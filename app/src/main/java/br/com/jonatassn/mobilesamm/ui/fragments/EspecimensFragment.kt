package br.com.jonatassn.mobilesamm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.com.jonatassn.mobilesamm.R
import br.com.jonatassn.mobilesamm.adapter.EspecimenAdapter
import br.com.jonatassn.mobilesamm.adapter.EspecimenAdapterListener
import br.com.jonatassn.mobilesamm.db.AppDatabase
import br.com.jonatassn.mobilesamm.db.dao.EspecimenDao
import br.com.jonatassn.mobilesamm.entities.Especimen
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_especimens.*
import kotlinx.android.synthetic.main.fragment_especimens.view.*


class EspecimensFragment : Fragment(), EspecimenAdapterListener {

    lateinit var adapter : EspecimenAdapter
    lateinit var especimenDao : EspecimenDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewInflada = inflater.inflate(R.layout.fragment_especimens, container, false)
        val fab : FloatingActionButton = viewInflada.findViewById(R.id.btAddEspecimen)

        val db = Room.databaseBuilder(
            requireContext(), AppDatabase::class.java, "samm.db"
        ).allowMainThreadQueries().build()

        fab.setOnClickListener {
            val especimen = Especimen("", 0, "", "", 0, false)
            adapter.addEspecimen(especimen)

            rvEspecimens.scrollToPosition(0)
        }

        especimenDao = db.especimenDao()
        roomSetup(viewInflada.rvEspecimens)



        return viewInflada
    }

    private fun roomSetup(rvEspecimens : RecyclerView) {
        val especimens = especimenDao.getAll()

        loadData(rvEspecimens, especimens)
    }

    private fun loadData(rvEspecimens : RecyclerView, especimens : List<Especimen>) {
        adapter  = EspecimenAdapter(especimens.toMutableList(), this)

        adapter.notifyDataSetChanged()
        rvEspecimens.adapter = adapter
        rvEspecimens.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager?

    }

    override fun onSaved(especimen: Especimen) {
        if(especimen.tagNumber == 0L) {
            Toast.makeText(requireContext(), R.string.empty_tag_number_error_message, Toast.LENGTH_SHORT).show()
        }
        else {
            especimenDao.insert(especimen)
        }
    }

    override fun onEdited(especimen: Especimen) {
        especimenDao.update(especimen)
    }

    override fun onDeleted(especimen: Especimen) {
        especimenDao.delete(especimen)
    }

}