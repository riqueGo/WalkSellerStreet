package com.rique.walksellerstreet.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

class SellerRepository : ISellerRepository {
    private val db = FirebaseFirestore.getInstance()

    override fun updateSellerLocation(sellerId: String, latitude: Double, longitude: Double) {
        val sellerRef = db.collection("sellers").document(sellerId)
        val geoPoint = GeoPoint(latitude, longitude)
        sellerRef.update("position", geoPoint, "isActive", true)
    }

    override fun updateIsActiveSeller(sellerId: String, isActive: Boolean) {
        val sellerRef = db.collection("sellers").document(sellerId)
        sellerRef.update("isActive", isActive)
    }
}