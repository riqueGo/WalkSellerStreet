package com.rique.walksellerstreet.repository

interface ISellerRepository {
    fun updateSellerLocation(sellerId: String, latitude: Double, longitude: Double)
    fun updateIsActiveSeller(sellerId: String, isActive: Boolean)
}