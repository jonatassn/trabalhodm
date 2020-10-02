package br.com.jonatassn.mobilesamm.adapter

import br.com.jonatassn.mobilesamm.entities.Especimen

interface EspecimenAdapterListener {
    fun onSaved(especimen : Especimen)
    fun onEdited(especimen: Especimen)
    fun onDeleted(especimen: Especimen)
}