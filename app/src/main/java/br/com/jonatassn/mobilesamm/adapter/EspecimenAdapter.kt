package br.com.jonatassn.mobilesamm.adapter

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.jonatassn.mobilesamm.R
import br.com.jonatassn.mobilesamm.entities.Especimen
import kotlinx.android.synthetic.main.especimen_view_expanded.view.*
import kotlinx.android.synthetic.main.especimen_view_shrinked.view.*
import kotlinx.android.synthetic.main.especimen_view_shrinked.view.tvNickname

class EspecimenAdapter(
    private var especimens : MutableList<Especimen>,
    private var listener : EspecimenAdapterListener
) : RecyclerView.Adapter<EspecimenAdapter.ViewHolder>()
{
    var editingEspecimen : Especimen? = null
    var newOne : Boolean = false
    var editing : Boolean = false

    fun addEspecimen(especimen : Especimen) : Int {
        if(editingEspecimen == null) {
            especimens.add(0, especimen)
            notifyItemInserted(0)
            editingEspecimen = especimen
            editing = false
            newOne = true
        }

        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))


    override fun getItemViewType(position: Int): Int {
        return if(editingEspecimen != null && position == especimens.indexOf(editingEspecimen!!)) {
            R.layout.especimen_view_expanded
        }
        else {
            R.layout.especimen_view_shrinked
        }

    }

    override fun getItemCount() = especimens.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun fillUi(especimen : Especimen) {

            if(itemViewType == R.layout.especimen_view_shrinked) {
                itemView.shrkedEtNickname.text = especimen.nickname
                itemView.shrkedEtTagNumber.text = especimen.tagNumber.toString()
                itemView.setOnClickListener {
                    if(!newOne) {
                        editingEspecimen = especimen
                        editing = true
                        notifyItemChanged(especimens.indexOf(especimen))
                    }
                }
                itemView.setOnLongClickListener {
                    especimen.done = !especimen.done
                    notifyItemChanged(especimens.indexOf(especimen))
                    listener.onEdited(especimen)
                    true
                }
                var card = itemView as CardView
                if (especimen.done) {
                    card.setCardBackgroundColor(Color.GREEN)
                    itemView.tvNickname.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                }
                else {
                    card.setCardBackgroundColor(0)
                    itemView.tvNickname.paintFlags = 0
                }

            }
            else {
                itemView.etNickname.text.clear()
                itemView.etAge.text.clear()
                itemView.etTagNumber.text.clear()
                itemView.etSex.text.clear()
                itemView.etBioInfo.text.clear()
                itemView.etNickname.setText(especimen.nickname)
                itemView.etAge.setText(especimen.age.toString())
                itemView.etTagNumber.setText(especimen.tagNumber.toString())
                itemView.etSex.setText(especimen.sex)
                itemView.etBioInfo.setText(especimen.biometricInfo)

                itemView.btDelete.setOnClickListener {
                    with(this@EspecimenAdapter) {
                        val position = especimens.indexOf(especimen)
                        especimens.removeAt(position)
                        notifyItemRemoved(position)
                        editingEspecimen = null
                        listener.onDeleted(especimen)
                        newOne = false
                    }
                }

                itemView.btSave.setOnClickListener {
                    especimen.nickname = itemView.etNickname.text.toString()
                    especimen.age = itemView.etAge.text.toString().toInt()
                    especimen.tagNumber = itemView.etTagNumber.text.toString().toLong()
                    especimen.sex = itemView.etSex.text.toString()
                    especimen.biometricInfo = itemView.etBioInfo.text.toString()
                    val position = especimens.indexOf(especimen)
                    if(especimen.tagNumber == 0L) {

                        //taskies.removeAt(position)
                        //notifyItemRemoved(position)
                        with(this@EspecimenAdapter) {
                            //editingTask = null
                            listener.onSaved(especimen)
                        }
                    }else {
                        if(editing) {
                            with(this@EspecimenAdapter) {
                                editingEspecimen = null
                                notifyItemChanged(position)
                                listener.onEdited(especimen)
                            }
                        }else {
                            with(this@EspecimenAdapter) {
                                editingEspecimen = null
                                editing = false
                                notifyItemChanged(position)
                                listener.onSaved(especimen)
                            }
                        }
                        newOne = false
                    }
                }

            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val especimen = especimens[position]
        holder.fillUi(especimen)
    }

}