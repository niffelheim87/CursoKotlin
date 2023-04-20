package com.ompava.masterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ompava.masterdetail.databinding.FragmentDetailBinding
import com.ompava.masterdetail.model.SuperHero
import com.ompava.masterdetail.model.SuperHeroProvider

private const val ARG_HERO = "ARG_HERO"

class DetailFragment : Fragment() {
    private var superHeroId: Int? = null
    private var superHero: SuperHero? = null
    private var _binding: FragmentDetailBinding? = null
    private lateinit var provider: SuperHeroProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            superHeroId = it.getInt(ARG_HERO, -1)
            provider = SuperHeroProvider()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tvSuperhero).text = superHero?.superhero
        view.findViewById<TextView>(R.id.tvRealName).text = superHero?.realName
        view.findViewById<TextView>(R.id.tvDescription).text = superHero?.publisher

        _binding?.ivPoster?.let {
            Glide.with(it.context).load(superHero?.photo).into(_binding!!.ivPoster)
        }

    }


    companion object {
        @JvmStatic
        fun newInstance(heroId: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_HERO, heroId)
            }
        }
    }
}