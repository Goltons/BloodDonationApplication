package com.example.blooddonationapplication.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.blooddonationapplication.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

  private lateinit var galleryViewModel: GalleryViewModel
private var _binding: FragmentGalleryBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

    _binding = FragmentGalleryBinding.inflate(inflater, container, false)
    val root: View = binding.root
  /*galleryViewModel.text.observe(viewLifecycleOwner, Observer {
    galleryViewModel.text= MutableLiveData(it)
    })*/
    return root
  }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*val recyclerView: RecyclerView = binding.rvTest
        //val adapter=RecycleViewAdapter(galleryViewModel.testList())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecycleViewAdapter(galleryViewModel.testList())*/

    }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}