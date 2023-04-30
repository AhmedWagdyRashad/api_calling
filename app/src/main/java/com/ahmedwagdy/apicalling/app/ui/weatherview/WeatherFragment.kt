package com.ahmedwagdy.apicalling.app.ui.weatherview

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.room.util.query
import com.ahmedwagdy.apicalling.R
import com.ahmedwagdy.apicalling.app.ui.weatherview.presenter.WeatherViewModel
import com.ahmedwagdy.apicalling.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private val viewModel by viewModels<WeatherViewModel>()
    val TAG = "WeatherFragment"
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherDataAdapter: WeatherDataAdapter
    private var cityQuery = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWeatherBinding.bind(view)
        weatherDataAdapter = WeatherDataAdapter()
        binding.itemRv.adapter = weatherDataAdapter
        showBeginningTV()
        crateMenu()
        binding.retryBtn.setOnClickListener {
            viewModel.getWeatherData(cityQuery)
            afterDataIsLoaded()
        }
    }

    private fun crateMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_weather_city_search, menu)
                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {

                        if (query != null) {
                            cityQuery = query
                            binding.itemRv.scrollToPosition(0)
                            showProgressBar()
                            viewModel.getWeatherData(query)
                            afterDataIsLoaded()
                            searchView.clearFocus()
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> {
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun afterDataIsLoaded(){
       hideBeginningTV()
        lifecycleScope.launch {
            viewModel.weatherData.collect{ it ->
                hideProgressBar()
                if (it.isNullOrEmpty()){
                    weatherDataAdapter.currentList.clear()
                   viewModel.error.collect{hasError ->
                      checkErrorView(hasError)
                  }
                }else{
                    weatherDataAdapter.submitList(it)

                }
            }
        }
    }
    private fun checkErrorView(hasError:Boolean){
            if (hasError){
                binding.errorTv.visibility = View.VISIBLE
                binding.retryBtn.visibility = View.VISIBLE
                binding.itemRv.visibility = View.GONE
            }else{
                binding.errorTv.visibility = View.GONE
                binding.retryBtn.visibility = View.GONE
                binding.itemRv.visibility = View.VISIBLE
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        binding.progressBar.visibility = View.GONE
    }

    private fun showBeginningTV(){
        binding.beginSearchTv.visibility = View.VISIBLE
    }
    private fun hideBeginningTV(){
        binding.beginSearchTv.visibility = View.GONE
    }
}