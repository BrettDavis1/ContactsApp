package com.example.contactsapp.view

import android.content.Intent
import android.os.Bundle
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
import com.example.contactsapp.databinding.FragmentContactsBinding
import com.example.contactsapp.model.Contact
import com.example.contactsapp.viewmodel.ContactsFragmentViewModel
import kotlin.math.log


class ContactsFragment : Fragment() {
    private lateinit var binding: FragmentContactsBinding
    private val viewModel by viewModels<ContactsFragmentViewModel>()
//    private val arguments by navArgs<>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getContacts(requireContext())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
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
        viewModel.getContacts(requireContext())
        viewModel.contacts.observe(this.viewLifecycleOwner, Observer {
            binding.rvContacts.layoutManager = LinearLayoutManager(this.context)
            binding.rvContacts.adapter = it?.let { contacts -> ContactAdapter(contacts, listener) }
        })
    }
}