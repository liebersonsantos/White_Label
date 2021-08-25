package br.com.liebersonsantos.whitelabel.data

import android.net.Uri
import br.com.liebersonsantos.whitelabel.BuildConfig
import br.com.liebersonsantos.whitelabel.domain.model.Product
import br.com.liebersonsantos.whitelabel.util.COLLECTION_PRODUCTS
import br.com.liebersonsantos.whitelabel.util.COLLECTION_ROOT
import br.com.liebersonsantos.whitelabel.util.STORAGE_IMAGES
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 * Created by lieberson on 8/11/21.
 * @author lieberson.xsantos@gmail.com
 */
class FirebaseProductDataSource @Inject constructor(
    firebaseFirestore: FirebaseFirestore,
    firebaseStorage: FirebaseStorage
): ProductDataSource {

    private val documentReference = firebaseFirestore
        .document("$COLLECTION_ROOT/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}/")
    private val storeReference = firebaseStorage.reference


    override suspend fun getProducts(): List<Product> = suspendCoroutine { continuation ->
            val productsReference = documentReference.collection(COLLECTION_PRODUCTS)
            productsReference.get().addOnSuccessListener { documents ->
                val products = mutableListOf<Product>()
                for (document in documents){
                    document.toObject(Product::class.java).run {
                        products.add(this)
                    }
                }

                continuation.resumeWith(Result.success(products))
            }

            productsReference.get().addOnFailureListener {
                continuation.resumeWith(Result.failure(it))
            }
        }

    override suspend fun uploadProductImage(imageUri: Uri): String = suspendCoroutine { continuation ->
            val randomKey = UUID.randomUUID() //gera um id aleatório
            val childReference = storeReference.child(
                "$STORAGE_IMAGES/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}/$randomKey"
            )

            childReference.putFile(imageUri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                        val path = uri.toString()
                        continuation.resumeWith(Result.success(path))
                    }

                }.addOnFailureListener { ex ->
                    continuation.resumeWith(Result.failure(ex))
                }
        }

    override suspend fun createProduct(product: Product): Product = suspendCoroutine { continuation ->
        documentReference
            .collection(COLLECTION_PRODUCTS)
            .document(System.currentTimeMillis().toString())
            .set(product)
            .addOnSuccessListener {
                continuation.resumeWith(Result.success(product))
            }
            .addOnFailureListener { ex ->
                continuation.resumeWith(Result.failure(ex))
            }
    }
}