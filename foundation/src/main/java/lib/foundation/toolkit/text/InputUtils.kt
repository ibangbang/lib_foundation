package lib.foundation.toolkit.text

import android.widget.EditText

object InputUtils {

    fun getInput(editText: EditText?): String? {

        return editText?.text?.toString()?.trim { it <= ' ' }

    }

    fun getInputWithoutSpace(editText: EditText?): String? {

        return editText?.text?.toString()?.trim { it <= ' ' }?.replace(" ", "")

    }

}