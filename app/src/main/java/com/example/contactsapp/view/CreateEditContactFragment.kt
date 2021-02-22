package com.example.contactsapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.compose.getBackStackEntry
import androidx.navigation.compose.navArgument
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentCreateEditContactBinding
import com.example.contactsapp.model.Address
import com.example.contactsapp.model.Contact
import com.example.contactsapp.viewmodel.CreateEditContactFragmentViewModel

class CreateEditContactFragment : Fragment() {
    private lateinit var binding: FragmentCreateEditContactBinding
    private val arguments by navArgs<CreateEditContactFragmentArgs>()
    private val viewModel by viewModels<CreateEditContactFragmentViewModel>()

    private val TAG = "CREATE_EDIT_FRAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCreateEditContactBinding.inflate(
        inflater,
        container,
        false
    ).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var isEdit: Boolean = false
//        if null then normal edit view not null show loaded contact in view
        if(arguments.selectedContact != null) {
            binding.etStreetAddress.setText(arguments.selectedContact?.address?.streetAddress)
            binding.etZipcode.setText(arguments.selectedContact?.address?.zipcode.toString())
            binding.etState.setText(arguments.selectedContact?.address?.state)
            binding.etCity.setText(arguments.selectedContact?.address?.city)
            binding.etEmails.setText(arguments.selectedContact?.email?.let { listToString(it) })
            binding.etFirstName.setText(arguments.selectedContact?.fName)
            binding.etLastName.setText(arguments.selectedContact?.lName)
            binding.etPhoneNumbers.setText(arguments.selectedContact?.phone?.let { listToString(it) })
            isEdit = true
        }
        binding.btnSave.setOnClickListener {
            // get listFields
            val emails = binding.etEmails.text.toString()
            val phoneNumbers = binding.etPhoneNumbers.text.toString()
            val emailList: List<String> = emails.split(",").map { emails.trim() }
            val phoneNumberList: List<String> = phoneNumbers.split(",").map { phoneNumbers.trim() }
//            val stringList: List<String> = phoneNumbers.split(",").map { phoneNumbers.trim() }
//            val phoneNumberList = convertToLong(stringList)
            val firstName: String = binding.etFirstName.text.toString()
            val lastName: String = binding.etLastName.text.toString()
            val streetAddress: String = binding.etStreetAddress.text.toString()
            val city: String = binding.etCity.text.toString()
            val state: String = binding.etState.text.toString()
            val zipcode: Int = binding.etZipcode.text.toString().toInt()
            val address = Address(streetAddress, city,state,zipcode)
            val contact = Contact(firstName,lastName, address, phoneNumberList, emailList)
            contact.id = arguments.selectedContact?.id ?: contact.id
            if(isEdit) {
                viewModel.updateContact(contact)
            } else {
                viewModel.saveContact(contact)
            }
            val action = CreateEditContactFragmentDirections.actionEditToContact(contact)
            findNavController().navigate(action)
        }
    }
    fun listToString(list: List<String>) : String {
        var s = ""
        for(string in list) {
            s += if(s == "") {
                string
            } else {
                ",$string"
            }
        }
        return s
    }
    // was used when i need int did work for longs
//    fun convertToLong(stringList: List<String>) : List<Long> {
//        var intList: List<Long> = emptyList()
//        for(string in stringList) {
//            Log.i("CREATE_EDIT_FRAG", "convertToInt: $string")
//            intList += string.toLong()
//        }
//        return intList
//    }
}