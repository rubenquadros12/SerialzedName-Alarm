package com.ruben.update

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.ruben.update.UpdateAction
import org.apache.maven.artifact.versioning.ComparableVersion

/**
 * Created by Ruben Quadros on 05/04/22
 **/
class UpdateChecker : StartupActivity {

    override fun runActivity(project: Project) {
        checkNewVersion(project)
    }

    private fun checkNewVersion(project: Project) {
        val installedVersion = "0.0"
        val pluginVersion = PluginManagerCore.getPlugin(PluginId.getId("com.ruben.serialized-alarm"))?.version

        val s1 = installedVersion.split("-")[0]
        val s2 = pluginVersion?.let {
            it.split("-")[0]
        }

        val v1 = ComparableVersion(s1)
        val v2 = ComparableVersion(s2)

        if (v1 < v2) {
            showUpdatePopup(project, s2)
        }
    }

    private fun showUpdatePopup(project: Project, newVersion: String?) {
        NotificationGroupManager.getInstance()
            .getNotificationGroup("Update Notification Group")
            .createNotification(
                content = newVersion?.let {
                    "A new version ($newVersion) of SerializedName Alarm is available. Please upgrade to the latest version"
                } ?: "A new version of SerializedName Alarm is available. Please upgrade to the latest version",
                type = NotificationType.IDE_UPDATE
            )
            .addAction(UpdateAction())
            .notify(project)
    }
}