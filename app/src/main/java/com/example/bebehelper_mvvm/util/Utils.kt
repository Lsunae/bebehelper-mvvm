package com.example.bebehelper_mvvm.util

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.bebehelper_mvvm.App

interface Utils {
    companion object {
        fun drawable(c: Context, resourceId: Int): Drawable? {
            var resourceId = resourceId
            return if (c.resources == null) null else try {
                c.getDrawable(resourceId)
            } catch (e: NotFoundException) {
                Log.e("ERROR", "[" + c.javaClass.name + "] getResources drawable NotFound : " + e)
                resourceId = c.resources.getIdentifier("ic_noimage", "drawable", c.packageName)
                ContextCompat.getDrawable(c, resourceId)
            }
        }

        fun color(c: Context, name: String): Int {
            return if (c.resources == null) com.google.android.material.R.color.design_default_color_primary else try {
                val resourceId = c.resources.getIdentifier(name, "color", c.packageName)
                ContextCompat.getColor(c, resourceId)
            } catch (e: NotFoundException) {
                Log.e("ERROR", "[" + c.javaClass.name + "] getResources color NotFound : " + e)
                com.google.android.material.R.color.design_default_color_primary
            }
        }

        fun string(c: Context, resourceId: Int): String {
            return if (c.resources == null) "" else try {
                c.getString(resourceId)
            } catch (e: NotFoundException) {
                Log.e("ERROR", "[" + c.javaClass.name + "] getResources string NotFound : " + e)
                ""
            }
        }

        fun string(c: Context, resourceId: Int, vararg arg: Any?): String {
            return if (c.resources == null) "" else try {
                c.getString(resourceId, *arg)
            } catch (e: NotFoundException) {
                Log.e("ERROR", "[" + c.javaClass.name + "] getResources string NotFound : " + e)
                ""
            }
        }

        fun getDeviceWidth(): Int {
            val dm: DisplayMetrics = App.instance.context().resources.displayMetrics
            return dm.widthPixels
        }

        fun getDeviceHeight(): Int {
            val dm: DisplayMetrics = App.instance.context().resources.displayMetrics
            return dm.heightPixels
        }
    }
}
