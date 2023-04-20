package com.ompava.masterdetail

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ompava.masterdetail.databinding.FragmentDetailBinding
import com.ompava.masterdetail.model.SuperHero
import com.ompava.masterdetail.model.SuperHeroProvider

private const val ARG_HERO = "ARG_HERO"

class DetailFragment(val fragmentContext: Context) : Fragment() {
    private var superHeroId: Int? = null
    private var superHero: SuperHero? = null
    private var _binding: FragmentDetailBinding? = null
    private lateinit var provider: SuperHeroProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            superHeroId = it.getInt(ARG_HERO, -1)
            provider = SuperHeroProvider(fragmentContext)
            superHero = provider.getSuperHeroById(superHeroId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    @SuppressLint("DiscouragedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.tvSuperhero?.text = superHero?.superhero
        _binding?.tvRealName?.text = superHero?.realName
        _binding?.tvDescription?.text = superHero?.publisher
        val imageName = superHero?.image
        val id = view.context.resources.getIdentifier(imageName, "drawable", view.context.packageName)
        _binding?.ivPoster?.setImageResource(id)
        /*_binding?.ivPoster?.let {
            Glide.with(it.context).load(superHero?.image).into(_binding!!.ivPoster)
        }
    */

    }


    companion object {
        @JvmStatic
        fun newInstance(context: Context, heroId: Int) = DetailFragment(context).apply {
            arguments = Bundle().apply {
                putInt(ARG_HERO, heroId)
            }
        }
    }
}