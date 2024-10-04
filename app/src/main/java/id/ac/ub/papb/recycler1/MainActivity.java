package id.ac.ub.papb.recycler1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Mahasiswa> mahasiswaList;
    private EditText etNim, etNama;
    private Button btSimpan;
    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.editTextTextPersonName2);
        btSimpan = findViewById(R.id.bt1);
        recyclerView = findViewById(R.id.rv1);

        // Initialize Mahasiswa list
        mahasiswaList = new ArrayList<>();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mahasiswaAdapter = new MahasiswaAdapter(mahasiswaList);
        recyclerView.setAdapter(mahasiswaAdapter);

        // Set onClickListener for Save button
        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input from EditText fields
                String nim = etNim.getText().toString().trim();
                String nama = etNama.getText().toString().trim();

                // Check if both fields are filled
                if (nim.isEmpty()) {
                    etNim.setError("NIM tidak boleh kosong");
                    return;
                }

                if (nama.isEmpty()) {
                    etNama.setError("Nama tidak boleh kosong");
                    return;
                }

                // Add new Mahasiswa to the list
                mahasiswaList.add(new Mahasiswa(nim, nama));

                // Notify adapter about the data change
                mahasiswaAdapter.notifyDataSetChanged();

                // Clear the input fields
                etNim.setText("");
                etNama.setText("");

                // Optionally show a success message
                Toast.makeText(MainActivity.this, "Mahasiswa berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
