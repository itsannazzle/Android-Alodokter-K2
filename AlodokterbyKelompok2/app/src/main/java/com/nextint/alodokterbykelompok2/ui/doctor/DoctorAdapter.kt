package com.nextint.alodokterbykelompok2.ui.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.data.local.DoctorEntity
import com.nextint.alodokterbykelompok2.databinding.ItemRvTopDoctorBinding

class DoctorAdapter : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {
    private var listDoctor = ArrayList<DoctorEntity>()

    fun setDoctor(doctors: List<DoctorEntity>?){
        if (doctors.isNullOrEmpty()) return
        this.listDoctor.clear()
        this.listDoctor.addAll(doctors)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val itemDoctorBinding = ItemRvTopDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorViewHolder(itemDoctorBinding)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = listDoctor[position]
        holder.bind(doctor)
    }

    override fun getItemCount(): Int = listDoctor.size

    class DoctorViewHolder(private val binding: ItemRvTopDoctorBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(doctor: DoctorEntity){
            with(binding){
                tvDoctorName.text = doctor.name
                tvDoctorSpecialist.text = doctor.spesialis
                Glide.with(itemView.context)
                    .load(doctor.img)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.image_doctor)
                            .error(R.drawable.no_image)
                    ).into(ivTopDoctor)
            }
        }
    }
}