package com.example.recipe

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe.adapters.OnRecipeListener
import com.example.recipe.adapters.RecipeRecyclerAdapter
import com.example.recipe.models.RecipeData
import com.example.recipe.util.VerticalSpacingItemDecorator
import com.example.recipe.viewmodels.RecipeListViewModel
import kotlinx.android.synthetic.main.activity_recipe_list.*

class RecipeListActivity : BaseActivity(), OnRecipeListener {

    private var mRecipeViewModel: RecipeListViewModel? = null
    private var adapter: RecipeRecyclerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeListViewModel::class.java)
        initRecyclerView()
        subscribeObserver()
        initSearchView()
    }

    private fun subscribeObserver() {
        mRecipeViewModel?.getRecipe()?.observe(this,
            Observer<MutableList<RecipeData>> {
                if (it != null) {
                    Log.e(TAG, "subscribeObserver: $it")
                    adapter?.setRecipe(it)
                }
            })
    }

    private fun initSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter?.displayLoading()
                mRecipeViewModel?.searchRecipesApi(query!!, 1)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun initRecyclerView() {
        adapter = RecipeRecyclerAdapter(this)
        recyclerView.addItemDecoration(VerticalSpacingItemDecorator(30))
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    companion object {
        private const val TAG = "RecipeListActivity"
    }

    override fun onRecipeClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCategoryClick(category: String) {
        TODO("Not yet implemented")
    }
}