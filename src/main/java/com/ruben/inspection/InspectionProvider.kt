package com.ruben.inspection

import com.intellij.codeInspection.InspectionToolProvider
import com.intellij.codeInspection.LocalInspectionTool

/**
 * Created by Ruben Quadros on 06/04/22
 **/
class InspectionProvider: InspectionToolProvider {
    override fun getInspectionClasses(): Array<Class<out LocalInspectionTool>> {
        return arrayOf(DataClassInspector::class.java)
    }
}