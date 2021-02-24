package com.example.contactsapp.view

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.R
import com.example.contactsapp.adapter.ContactAdapter
import com.example.contactsapp.adapter.ContactClickListener
import com.example.contactsapp.adapter.MyAdapter
import com.example.contactsapp.databinding.FragmentContactsBinding
import com.example.contactsapp.model.Contact
import com.example.contactsapp.model.Header
import com.example.contactsapp.util.ViewType
import com.example.contactsapp.viewmodel.ContactsFragmentViewModel
import com.google.android.material.radiobutton.MaterialRadioButton
import kotlin.math.log


class ContactsFragment : Fragment() {
    private lateinit var binding: FragmentContactsBinding
    private val viewModel by viewModels<ContactsFragmentViewModel>()
    private val NO_FILTER = 0
    private val FIRST_NAME_FILTER = 1
    private val LAST_NAME_FILTER = 2
//    private val arguments by navArgs<>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentContactsBinding.inflate(
        inflater,
        container,
        false
    ).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.rgFilter.check(binding.rbAll.id)  // doing this in xml now
        binding.btnCreateContact.setOnClickListener {
            val action = ContactsFragmentDirections.actionContactsFragmentToCreateEditContactFragment(null)
            findNavController().navigate(action)
        }
        val listener: ContactClickListener = object : ContactClickListener {
            override fun itemClicked(contact: Contact) {
                val action = ContactsFragmentDirections.actionContactsFragmentToDetailContactFragment(contact)
                findNavController().navigate(action)
            }
        }
        viewModel.getContacts()
//        viewModel.contacts.observe(this.viewLifecycleOwner, Observer {
//            binding.rvContacts.layoutManager = LinearLayoutManager(this.context)
//            binding.rvContacts.adapter = it?.let { contacts -> MyAdapter(convertContactsIntoViewType(contacts), listener) }
//        })
        var field = NO_FILTER
        var filter = binding.etField.text.toString()
        binding.btnFilter.setOnClickListener {
            val selectedOption: Int = binding.rgFilter.checkedRadioButtonId
            field = when(selectedOption) {
                binding.rbFirstName.id -> FIRST_NAME_FILTER
                binding.rbLastName.id -> LAST_NAME_FILTER
                else -> NO_FILTER
            }
            filter = binding.etField.text.toString()
            if(viewModel.contacts.value != null) {
                binding.rvContacts.adapter = it?.let {
                    MyAdapter(convertContactsIntoViewType(
                            filterBy(field, filter, viewModel.contacts.value!!) ), listener) }// check to make sure not null before calling
            }
        }
        viewModel.contacts.observe(this.viewLifecycleOwner, Observer {
            binding.rvContacts.layoutManager = LinearLayoutManager(this.context)
            binding.rvContacts.adapter = it?.let { contacts ->
                MyAdapter(convertContactsIntoViewType(
                    filterBy(field, filter, contacts)), listener) }
        })
    }
    private fun filterBy(field: Int, filter: String, contacts: List<Contact>) : List<Contact> {
        return when(field) {
            FIRST_NAME_FILTER -> contacts.filter {
                it.fName.contains(filter, true)
            }
            LAST_NAME_FILTER -> contacts.filter {
                it.lName.contains(filter, true)
            }
            else -> contacts
        }
    }
    private fun convertContactsIntoViewType(contacts: List<Contact>) : List<ViewType> {
        val viewTypeList = mutableListOf<ViewType>()
        var headerString = ""
        for(contact in contacts) {
            if (contact.fName[0].toUpperCase().toString() == headerString) {
                viewTypeList.add(contact)
            } else {
                headerString = contact.fName[0].toUpperCase().toString()
                viewTypeList.add(Header(headerString))
                viewTypeList.add(contact)
            }
        }
        return viewTypeList
    }
}