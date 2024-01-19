package com.example.banhang.Login;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.banhang.R;
import com.example.banhang.View.HeaderMenu.HeaderFragment;
import com.example.banhang.View.HomeAdminActivity;
import com.example.banhang.database.CreateDatabase;

public class KhungDangNhapActivity extends AppCompatActivity {
    CreateDatabase databaseHelper;
    EditText edtTenDangNhapDN, edtMatKhauDN;
    Button btDangNhapDN, btDangKyDN;
    CheckBox cbSaveAccount;
    String thongtinluu = "tk_mk login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        databaseHelper = new CreateDatabase(this);
        AnhXa();

        cbSaveAccount.setChecked(true);

        btDangKyDN.setOnClickListener(v -> {
            Intent intent = new Intent(KhungDangNhapActivity.this, KhungDangKyActivity.class);
            startActivity(intent);
        });
        btDangNhapDN.setOnClickListener(v -> {
            String tenDangNhap = edtTenDangNhapDN.getText().toString().trim();
            String matKhauDangNhap = edtMatKhauDN.getText().toString().trim();
            if (tenDangNhap.equals("") || matKhauDangNhap.equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Vui Lòng Nhập Tài Khoản Hoặc Mật Khẩu", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (checkLogin(tenDangNhap, matKhauDangNhap)) {
                    // Lưu Thông Tin Đăng Nhập
                    SharedPreferences sharedPreferences = getSharedPreferences(thongtinluu, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    // Lưu Theo Dạng Phân rả
                    editor.putString("Username", edtTenDangNhapDN.getText().toString());
                    editor.putString("Password", edtMatKhauDN.getText().toString());
                    editor.putBoolean("Save", cbSaveAccount.isChecked());
                    editor.apply();
                    // Cập nhật tên đăng nhập

                    String matkhauDatabase = databaseHelper.getMatKhauDatabase(tenDangNhap);
                    String tenDangNhapDatabase = databaseHelper.getClTenDangnhapDatabase(tenDangNhap);
                    if (matKhauDangNhap.equals(matkhauDatabase) && tenDangNhap.equals(tenDangNhapDatabase)) {
                        Intent intentDangnhap = new Intent(KhungDangNhapActivity.this, HomeAdminActivity.class);
                        startActivity(intentDangnhap);
                        Toast toast = Toast.makeText(getApplicationContext(), "Đăng nhập Thành Công !", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(), "Thông Tin Đăng Nhập Không Chính Xác", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    // Tạo Class để ánh xạ các biên bên layout đăng nhập
    public void AnhXa() {
        edtTenDangNhapDN = findViewById(R.id.edtTenDangNhapDN);
        edtMatKhauDN = findViewById(R.id.edtMatKhauDN);
        btDangNhapDN = findViewById(R.id.btDangNhapDN);
        btDangKyDN = findViewById(R.id.btDangKyDN);
        cbSaveAccount = findViewById(R.id.cbSaveAccount);
    }

    // Hàm check User Có tồn tại trong database
    private boolean checkLogin(String username, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "SELECT * FROM " + CreateDatabase.TB_DANG_NHAP_KHACH_HANG +
                " WHERE " + CreateDatabase.CL_TEN_DANGNHAP + "=? AND " + CreateDatabase.CL_MAT_KHAU + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        boolean result = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return result;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Hiển thị thông tin đã được lưu trữ
        SharedPreferences sharedPreferences = getSharedPreferences(thongtinluu, MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "");
        String password = sharedPreferences.getString("Password", "");
        boolean save = sharedPreferences.getBoolean("Save", false);
        if (save) {
            edtTenDangNhapDN.setText(username);
            edtMatKhauDN.setText(password);
        }
    }
}
