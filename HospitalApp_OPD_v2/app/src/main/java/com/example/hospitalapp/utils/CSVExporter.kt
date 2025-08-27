package com.example.hospitalapp.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.example.hospitalapp.data.models.OutpatientVisit
import java.io.File

object CSVExporter {
    fun visitsToCsv(visits: List<OutpatientVisit>): String {
        val header = listOf("date","time","patientName","age","sex","phone","newVisit","chiefComplaint","diagnosis","icdCode","treatment","medications","vitalsBp","vitalsPulse","vitalsTemp","followUpDate","fee","referredTo","notes")
        val lines = visits.map { v ->
            listOf(
                v.date, v.time ?: "", v.patientName, v.age?.toString() ?: "", v.sex ?: "", v.phone ?: "",
                v.newVisit.toString(), v.chiefComplaint, v.diagnosis, v.icdCode ?: "", v.treatment, v.medications,
                v.vitalsBp ?: "", v.vitalsPulse ?: "", v.vitalsTemp ?: "", v.followUpDate ?: "",
                v.fee?.toString() ?: "", v.referredTo ?: "", v.notes.replace("\n"," ")
            ).joinToString(",") { it.wrapCsv() }
        }
        return (listOf(header.joinToString(",")) + lines).joinToString("\n")
    }

    private fun String.wrapCsv(): String {
        val needsQuotes = this.contains(",") || this.contains("\n") || this.contains("\"")
        val escaped = this.replace("\"", "\"\"")
        return if (needsQuotes) "\"$escaped\"" else escaped
    }

    fun shareCsv(context: Context, csv: String, filename: String = "opd_export.csv") {
        val cacheDir = File(context.cacheDir, "exports")
        cacheDir.mkdirs()
        val f = File(cacheDir, filename)
        f.writeText(csv)
        val uri = FileProvider.getUriForFile(context, context.packageName + ".provider", f)

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/csv"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, "Share OPD CSV"))
    }
}
