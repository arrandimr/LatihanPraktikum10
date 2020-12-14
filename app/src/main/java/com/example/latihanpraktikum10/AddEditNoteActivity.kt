package com.example.latihanpraktikum10

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddEditNoteActivity : AppCompatActivity() {
    companion object {
        //inisiasi variabel intent
        const val EXTRA_ID = "com.example.praktikum10.EXTRA_ID"
        const val EXTRA_JUDUL = "com.example.praktikum10.EXTRA_JUDUL"
        const val EXTRA_DESKRIPSI = "com.example.praktikum10.EXTRA_DESKRIPSI"
        const val EXTRA_REFERENSI = "com.example.praktikum10.EXTRA_NOTELP"
        const val EXTRA_PRIORITAS = "com.example.praktikum10.EXTRA_PRIORITAS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        //set number picker
        number_picker_priority.minValue = 1
        number_picker_priority.maxValue = 5
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)

        //jika data telah ditemukan maka edit data
        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Catatan"
            edit_text_title.setText(intent.getStringExtra(EXTRA_JUDUL))
            edit_text_description.setText(intent.getStringExtra(EXTRA_DESKRIPSI))
            edit_text_referensi.setText(intent.getStringExtra(EXTRA_REFERENSI))
            number_picker_priority.value = intent.getIntExtra(EXTRA_PRIORITAS, 1)
        }
        //jika tidak maka tambah data
        else {
            title = "Tambah Catatan"
        }
    }

    //memunculkan layout add_note_menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true     }

    //jika tombolsave ditekan akan menjalankan methodsave_note
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //proses simpan data
    private fun saveNote() {
        //jika terdapat inputan yang kosong --> muncul peringatan
        if (edit_text_title.text.toString().trim().isBlank() || edit_text_description.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Catatan kosong!", Toast.LENGTH_SHORT).show()
            return
        }

        //untuk menyimpan data pada variabel intent
        val data = Intent().apply {
            putExtra(EXTRA_JUDUL, edit_text_title.text.toString())
            putExtra(EXTRA_DESKRIPSI, edit_text_description.text.toString())
            putExtra(EXTRA_REFERENSI, edit_text_referensi.text.toString())
            putExtra(EXTRA_PRIORITAS, number_picker_priority.value)
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}