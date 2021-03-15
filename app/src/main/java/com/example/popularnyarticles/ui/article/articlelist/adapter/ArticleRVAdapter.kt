package com.example.popularnyarticles.ui.article.articlelist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularnyarticles.R
import com.example.popularnyarticles.databinding.ArticleItemBinding
import com.example.popularnyarticles.extention.getPhotoUrl
import com.example.popularnyarticles.network.model.PopularArticle

class ArticleRVAdapter(private var articleList:List<PopularArticle>,
                       private val context:Context?,
                       private  val itemClickListener: OnItemClicked
):
    RecyclerView.Adapter<ArticleRVAdapter.ArticleViewHolder>() {

    interface OnItemClicked{
        fun onItemClicked(view: View, article: PopularArticle)

    }
    private lateinit var binding: ArticleItemBinding
    class ArticleViewHolder(private val viewBinding: ArticleItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {


        fun bindClickListener(article: PopularArticle, itemClickListener: OnItemClicked) {
            viewBinding.root.setOnClickListener {
                itemClickListener.onItemClicked(it, article)
            }
        }

        val articleImage = viewBinding.articleImage
        val articleTitle = viewBinding.articleTitle
        val articleBy = viewBinding.articleBy
        val articleDate = viewBinding.articleDate


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        this.binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articleList[position]
        holder.articleTitle.text = article.title
        holder.articleBy.text = article.byline
        holder.articleDate.text = article.published_date
        val photoURL = article.getPhotoUrl()

        holder.bindClickListener(article,itemClickListener)

        context?.let {
            Glide.with(it)
                .load(photoURL)
                .placeholder(R.drawable.placeholder_image)
                .into(holder.articleImage)
        }

    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun setArticles(articleList:List<PopularArticle>){
        this.articleList = articleList
        notifyDataSetChanged()
    }
}