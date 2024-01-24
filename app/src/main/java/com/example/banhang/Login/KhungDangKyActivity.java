package com.example.banhang.Login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banhang.R;
import com.example.banhang.database.CreateDatabase;

public class KhungDangKyActivity extends AppCompatActivity {
    //Khai Báo Các Biên Giao Diện
    EditText edtTenDangNhap,edtMatKhau,edtNhapLaiMatKhau,edtNgaySinh,edtCMND;
    Button btDongY,btThoat;
    RadioButton rdoNam,rdoNu;
    RadioGroup rdogrGioiTinh;
    CreateDatabase databaseHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);
        databaseHelper = new CreateDatabase(this);
        db = databaseHelper.getWritableDatabase();
        AnhXa();

        btDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenDangNhapDK = edtTenDangNhap.getText().toString();
                String MatKhauDK = edtMatKhau.getText().toString();
                String NhapLaiMatKhauDK = edtNhapLaiMatKhau.getText().toString();
                String NgaySinhDK = edtNgaySinh.getText().toString();
                String CMNDK = edtCMND.getText().toString();
                int selectedRadioButtonId = rdogrGioiTinh.getCheckedRadioButtonId();
                String gioiTinh;
                if (selectedRadioButtonId != -1) {
                    // Tìm RadioButton dựa trên ID
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                    // Lấy văn bản của RadioButton
                     gioiTinh = selectedRadioButton.getText().toString();

                } else {
                    gioiTinh = "";
                }
                if(TenDangNhapDK.equals("")||TenDangNhapDK.length()  < 5){
                    Toast toast = Toast.makeText(getApplicationContext(),"Ten Dang Nhap Dang Rong Va Phai Lon Hon 4 Ký Tự",Toast.LENGTH_SHORT);toast.show();

                } else if (MatKhauDK.equals("") || MatKhauDK.length()  <= 6) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Mật Khẩu Không Đươc Rỗng Và Phải Hơn 6 Kí Tự",Toast.LENGTH_SHORT);toast.show();

                } else if (NhapLaiMatKhauDK.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Bạn Chưa Nhập Lại Mật Khẩu",Toast.LENGTH_SHORT);toast.show();

                } else if (!NhapLaiMatKhauDK.equals(edtMatKhau.getText().toString())) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Mật Khẩu Bạn Nhập Không Trùng Khớp",Toast.LENGTH_SHORT);toast.show();
                } else if (rdogrGioiTinh.getCheckedRadioButtonId() == -1) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Hãy Chọn Giới Tính Của Bạn",Toast.LENGTH_SHORT);toast.show();
                } else if(NgaySinhDK.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"bạn Chưa Nhập Ngày Sinh",Toast.LENGTH_SHORT);toast.show();
                }else if(CMNDK.equals("") || CMNDK.length() != 12 ){
                    Toast toast = Toast.makeText(getApplicationContext(),"bạn Chưa Nhập CMND Và là  12 số",Toast.LENGTH_SHORT);toast.show();
                }else {
                    try {
                        DangKyNguoiDung(TenDangNhapDK,MatKhauDK,NgaySinhDK,CMNDK,gioiTinh);
                        Intent intent = new Intent(KhungDangKyActivity.this, KhungDangNhapActivity.class);
                        startActivity(intent);
                    }catch (Exception e){
                        Toast toast = Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT);toast.show();

                    }

                }

            }
        });
        findViewById(R.id.btThoat).setOnClickListener(v -> {
            Intent intent  = new Intent(KhungDangKyActivity.this,KhungDangNhapActivity.class);
            startActivity(intent);
        });
    }
    public  void AnhXa(){
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtNhapLaiMatKhau = findViewById(R.id.edtNhapLaiMatKhau);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtCMND = findViewById(R.id.edtCMND);
        btDongY = findViewById(R.id.btDongY);
        btThoat = findViewById(R.id.btThoat);
        rdoNam = findViewById(R.id.rdoNam);
        rdoNu = findViewById(R.id.rdoNu);
        rdogrGioiTinh = findViewById(R.id.rdogrGioiTinh);
    }
    //Method
    private void DangKyNguoiDung(String tenDangNhap, String matKhau,String ngaysinh,String cmnd,String gioitinh) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        /*
        ContentValues ContentValues là một lớp trong Android được sử dụng để đại diện cho một bộ giá trị, nơi mà mỗi giá trị được liên kết với một tên cột. Nó thường được sử dụng để chuẩn bị dữ liệu để chèn hoặc cập nhật vào cơ sở dữ liệu.

        +Một số phương thức quan trọng của lớp ContentValues bao gồm:

        +put(String key, String value): Thêm một cặp khóa/giá trị vào ContentValues. Loại giá trị được xác định bởi kiểu dữ liệu của giá trị được chuyển vào.

        +putAll(ContentValues other): Thêm tất cả các cặp khóa/giá trị từ một đối tượng ContentValues khác vào đối tượng hiện tại.

        +remove(String key): Loại bỏ cặp khóa/giá trị với khóa được chỉ định từ ContentValues.

        +Các phương thức put khác cho các kiểu dữ liệu khác nhau như put(String key, Integer value), put(String key, Long value), put(String key, Double value), vv.
        */
        try {
            if (!isTaiKhoanTonTai(tenDangNhap, cmnd)) {
                ContentValues values = new ContentValues();
                values.put(CreateDatabase.CL_TEN_DANGNHAP, tenDangNhap);
                values.put(CreateDatabase.CL_MAT_KHAU, matKhau);
                values.put(CreateDatabase.CL_NGAYSINH, ngaysinh);
                values.put(CreateDatabase.CL_CMND, cmnd);
                values.put(CreateDatabase.CL_GIOITINH, gioitinh);

                // Chèn dữ liệu vào bảng TB_DANG_NHAP_KHACH_HANG
                db.insert(CreateDatabase.TB_DANG_NHAP_KHACH_HANG, null, values);
                Toast toast = Toast.makeText(getApplicationContext(),"Đăng Ký Thành Công Nhé ~~",Toast.LENGTH_SHORT);toast.show();

            } else {
                // Xử lý khi tài khoản hoặc số điện thoại đã tồn tại
                Toast.makeText(this, "Tài Khoản Hoặc CMND Đã Tồn Tại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("DangKyNguoiDung", "Error during registration", e);
        }

    }
    // Phương thức kiểm tra xem tài khoản hoặc số điện thoại đã tồn tại chưa
    private boolean isTaiKhoanTonTai(String tenDangNhap, String cmnd) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + CreateDatabase.TB_DANG_NHAP_KHACH_HANG +
                " WHERE " + CreateDatabase.CL_TEN_DANGNHAP + "=? OR " + CreateDatabase.CL_CMND + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{tenDangNhap, cmnd});
        boolean result = cursor.getCount() > 0;
        cursor.close();

        return result;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Đóng đối tượng SQLiteDatabase khi Activity bị hủy

    }
}