package com.example.contactsapp.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contactsapp.databinding.FragmentDetailContactBinding
import com.example.contactsapp.model.Contact


class DetailContactFragment : Fragment() {
    private lateinit var binding: FragmentDetailContactBinding
    private val arguments by navArgs<DetailContactFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailContactBinding.inflate(
        inflater,
        container,
        false
    ).also { binding = it }.root

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvFirstName.text = arguments.selectedContact.fName
        binding.tvLastName.text = arguments.selectedContact.lName
        binding.tvPhoneNumbers.text = arguments.selectedContact.phone.toString()
        binding.tvEmails.text = arguments.selectedContact.email.toString()
        binding.tvStreetAddress.text = arguments.selectedContact.address.streetAddress
        binding.tvCity.text = arguments.selectedContact.address.city
        binding.tvState.text = arguments.selectedContact.address.state
        binding.tvZipcode.text = arguments.selectedContact.address.zipcode.toString()
        binding.btnEdit.setOnClickListener {
            val action = DetailContactFragmentDirections.actionDetailContactFragmentToCreateEditContactFragment(arguments.selectedContact)
            findNavController().navigate(action)
        }
    }
}