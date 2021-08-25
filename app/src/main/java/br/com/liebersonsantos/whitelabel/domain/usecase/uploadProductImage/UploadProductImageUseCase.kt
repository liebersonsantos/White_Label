package br.com.liebersonsantos.whitelabel.domain.usecase.uploadProductImage

import android.net.Uri

/**
 * Created by lieberson on 8/10/21.
 * @author lieberson.xsantos@gmail.com
 */
interface UploadProductImageUseCase {
    suspend operator fun invoke(imageUri: Uri): String
}