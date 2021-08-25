package br.com.liebersonsantos.whitelabel.config

import android.view.View
import javax.inject.Inject

/**
 * Created by lieberson on 8/13/21.
 * @author lieberson.xsantos@gmail.com
 */
class ConfigImpl @Inject constructor() : Config {
    override val addButtonVisibility: Int = View.GONE
}