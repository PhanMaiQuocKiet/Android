package com.example.banhang.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {
    //*****Cơ Sở Dữ Liệu*****
    //Tên Database
    private final static String DATABASE_NAME = "ShoppingSouvenir";

    //Version
    private final static int VERSION = 1;

    //Context

    // Bảng KhachHang
    private static final String TB_KHACH_HANG = "KhachHang";
    private static final String CL_KHACH_HANG_ID = "MaKhachHang";
    private static final String CL_TEN_KHACH_HANG = "TenKhachHang";
    private static final String CL_DIA_CHI = "DiaChi";
    private static final String CL_SO_DIEN_THOAI = "SoDienThoai";

    // Bảng NhanVien
    private static final String TB_NHAN_VIEN = "NhanVien";
    private static final String CL_NHAN_VIEN_ID = "MaNhanVien";
    private static final String CL_TEN_NHAN_VIEN = "TenNhanVien";
    private static final String CL_CHUC_VU = "ChucVu";
    // Bảng DangNhapKhachHang
    public static final String TB_DANG_NHAP_KHACH_HANG = "DangNhapKhachHang";
    public static final String CL_DANG_NHAP_ID = "MaDangNhap";
    public static final String CL_DANG_NHAP_KHACH_HANG_ID = "MaKhachHang";
    public static final String CL_TEN_DANGNHAP = "TenDangNhap";
    public static final String CL_NGAYSINH= "NGAYSINH";
    public static final String CL_CMND= "CMND";
    public static final String CL_GIOITINH= "GIOITINH";
    public static final String CL_MAT_KHAU = "MatKhau";
    public static final String CL_VAI_TRO = "VaiTro";

    // Bảng DonHang
    private static final String TB_DON_HANG = "DonHang";
    private static final String CL_DON_HANG_ID = "MaDonHang";
    private static final String CL_NGAY_DAT_HANG = "NgayDatHang";

    // Bảng SanPham
    private static final String TB_SAN_PHAM = "SanPham";
    private static final String CL_SAN_PHAM_ID = "MaSanPham";
    private static final String CL_TEN_SAN_PHAM = "TenSanPham";
    private static final String CL_GIA_BAN = "GiaBan";

    // Bảng DanhGiaKhachHang
    private static final String TB_DANH_GIA_KHACH_HANG = "DanhGiaKhachHang";
    private static final String  CL_Ma_DANH_GIA = "MADANHGIA";
    private static final String  CL_MA_KHACH_HANG_DANH_GIA = "MaKhachHangDanhGia";
    private static final String CL_DANHGIA_KHACH_HANG_ID = "MaKhachHang";
    // Bảng LoaiSanPham
    private static final String TB_LOAI_SAN_PHAM = "LoaiSanPham";
    private static final String CL_LOAI_SAN_PHAM_ID = "MaLoaiSanPham";
    private static  final String CL_TEN_LOAI_SAN_PHAM= "TENLOAISANPHAM";

    // Bảng NhaCungCap
    private static final String TB_NHA_CUNG_CAP = "NhaCungCap";
    private static final String CL_NHA_CUNG_CAP_ID = "MaNhaCungCap";
    private static final String CL_TEN_NHA_CUNG_CAP = "TENNHACUNGCAP";
    private static final String CL_SO_DIEN_THOAI_NCC = "SODIENTHOAINCC";
    private static final String CL_DIA_CHI_NCC = "DIACHINCC";

    // Bảng SanPhamCungCap
    private static final String TB_SAN_PHAM_CUNG_CAP = "SanPhamCungCap";
    public CreateDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }



    //Tạo Bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng và các cột
        String createTableQuery = "CREATE TABLE IF NOT EXISTS mytable (id INTEGER PRIMARY KEY, name TEXT)";
        db.execSQL(createTableQuery);
        // Tạo bảng KhachHang
        String CREATE_TABLE_KHACH_HANG = "CREATE TABLE " + TB_KHACH_HANG + "("
                + CL_KHACH_HANG_ID + " INTEGER PRIMARY KEY,"
                + CL_TEN_KHACH_HANG + " TEXT,"
                + CL_DIA_CHI + " TEXT,"
                + CL_SO_DIEN_THOAI + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE_KHACH_HANG);

        // Tạo bảng DangNhapKhachHang
        String CREATE_TABLE_DANG_NHAP_KHACH_HANG = "CREATE TABLE " + TB_DANG_NHAP_KHACH_HANG + "("
                + CL_DANG_NHAP_ID + " INTEGER PRIMARY KEY,"
                + CL_DANG_NHAP_KHACH_HANG_ID + " INTEGER,"
                + CL_TEN_DANGNHAP + " TEXT UNIQUE,"
                + CL_MAT_KHAU + " TEXT,"
                + CL_NGAYSINH + " DATE,"
                + CL_CMND + " INTEGER,"
                + CL_GIOITINH + " TEXT,"
                + CL_VAI_TRO + " TEXT,"
                + "FOREIGN KEY(" + CL_DANG_NHAP_KHACH_HANG_ID + ") REFERENCES " + TB_KHACH_HANG + "(" + CL_KHACH_HANG_ID + ")"
                + ")";
        db.execSQL(CREATE_TABLE_DANG_NHAP_KHACH_HANG);
        // Tạo bảng SanPham
        String CREATE_TABLE_SAN_PHAM = "CREATE TABLE " + TB_SAN_PHAM + "("
                + CL_SAN_PHAM_ID + " INTEGER PRIMARY KEY,"
                + CL_TEN_SAN_PHAM + " TEXT,"
                + CL_GIA_BAN + " REAL,"
                + CL_NHA_CUNG_CAP_ID + " INTEGER,"
                + "FOREIGN KEY(" + CL_NHA_CUNG_CAP_ID + ") REFERENCES " + TB_NHA_CUNG_CAP + "(" + CL_NHA_CUNG_CAP_ID + ")"
                + ")";
        db.execSQL(CREATE_TABLE_SAN_PHAM);

        // Tạo bảng NhanVien
        String CREATE_TABLE_NHAN_VIEN = "CREATE TABLE " + TB_NHAN_VIEN + "("
                + CL_NHAN_VIEN_ID + " INTEGER PRIMARY KEY,"
                + CL_TEN_NHAN_VIEN + " TEXT,"
                + CL_CHUC_VU + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE_NHAN_VIEN);

        // Tạo bảng DanhGiaKhachHang
        String CREATE_TABLE_DANH_GIA_KHACH_HANG = "CREATE TABLE " + TB_DANH_GIA_KHACH_HANG + "("
                + CL_Ma_DANH_GIA + " INTEGER PRIMARY KEY,"
                + CL_MA_KHACH_HANG_DANH_GIA + " INTEGER,"
                + CL_DANHGIA_KHACH_HANG_ID + " INTEGER,"
                + "FOREIGN KEY(" + CL_MA_KHACH_HANG_DANH_GIA + ") REFERENCES " + TB_KHACH_HANG + "(" + CL_KHACH_HANG_ID + ")"
                + ")";
        db.execSQL(CREATE_TABLE_DANH_GIA_KHACH_HANG);

        // Tạo bảng LoaiSanPham
        String CREATE_TABLE_LOAI_SAN_PHAM = "CREATE TABLE " + TB_LOAI_SAN_PHAM + "("
                + CL_LOAI_SAN_PHAM_ID + " INTEGER PRIMARY KEY,"
                + CL_TEN_LOAI_SAN_PHAM + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE_LOAI_SAN_PHAM);

        // Tạo bảng DonHang
        String CREATE_TABLE_DON_HANG = "CREATE TABLE " + TB_DON_HANG + "("
                + CL_DON_HANG_ID + " INTEGER PRIMARY KEY,"
                + CL_NGAY_DAT_HANG + " TEXT,"
                + CL_KHACH_HANG_ID + " INTEGER,"
                + "FOREIGN KEY(" + CL_KHACH_HANG_ID + ") REFERENCES " + TB_KHACH_HANG + "(" + CL_KHACH_HANG_ID + ")"
                + ")";
        db.execSQL(CREATE_TABLE_DON_HANG);

        // Tạo bảng NhaCungCap
         String CREATE_TABLE_NHA_CUNG_CAP = "CREATE TABLE " + TB_NHA_CUNG_CAP + "("
                + CL_NHA_CUNG_CAP_ID + " INTEGER PRIMARY KEY,"
                + CL_TEN_NHA_CUNG_CAP + " TEXT,"
                + CL_DIA_CHI_NCC + " TEXT,"
                + CL_SO_DIEN_THOAI_NCC + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE_NHA_CUNG_CAP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa và tạo lại bảng nếu có bất kỳ thay đổi cấu trúc nào
        db.execSQL("DROP TABLE IF EXISTS " + TB_KHACH_HANG);
        db.execSQL("DROP TABLE IF EXISTS " + TB_DANG_NHAP_KHACH_HANG);
        db.execSQL("DROP TABLE IF EXISTS " + TB_DON_HANG);
        db.execSQL("DROP TABLE IF EXISTS " + TB_SAN_PHAM);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NHAN_VIEN);
        db.execSQL("DROP TABLE IF EXISTS " + TB_DANH_GIA_KHACH_HANG);
        db.execSQL("DROP TABLE IF EXISTS " + TB_LOAI_SAN_PHAM);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NHA_CUNG_CAP);
        // Tạo lại bảng mới
        onCreate(db);
    }
 //method
    @SuppressLint("Range")
    public String getMatKhauDatabase(String tenDangNhap) {
        String matKhau = null;
        SQLiteDatabase db = this.getReadableDatabase();

        // Query để lấy mật khẩu từ cơ sở dữ liệu
        String query = "SELECT " + CreateDatabase.CL_MAT_KHAU + " FROM " + CreateDatabase.TB_DANG_NHAP_KHACH_HANG +
                " WHERE " + CreateDatabase.CL_TEN_DANGNHAP + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{tenDangNhap});

        // Kiểm tra xem có dữ liệu hay không
        if (cursor.moveToFirst()) {
            matKhau = cursor.getString(cursor.getColumnIndex(CreateDatabase.CL_MAT_KHAU));
        }

        // Đóng cursor và database
        cursor.close();
        db.close();

        return matKhau;
    }
    @SuppressLint("Range")
    public String getClTenDangnhapDatabase(String tenDangNhap) {
        String tendangnhap = null;
        SQLiteDatabase db = this.getReadableDatabase();

        // Query để lấy Tài Khoản từ cơ sở dữ liệu
        String query = "SELECT " + CreateDatabase.CL_TEN_DANGNHAP + " FROM " + CreateDatabase.TB_DANG_NHAP_KHACH_HANG +
                " WHERE " + CreateDatabase.CL_TEN_DANGNHAP + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{tenDangNhap});

        // Kiểm tra xem có dữ liệu hay không
        if (cursor.moveToFirst()) {
            tendangnhap = cursor.getString(cursor.getColumnIndex(CreateDatabase.CL_TEN_DANGNHAP));
        }

        // Đóng cursor và database
        cursor.close();
        db.close();

        return tendangnhap;
    }

    public void setAdminRoleForUser(String tenDangNhap) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Sử dụng ContentValues để chuẩn bị dữ liệu cần cập nhật
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.CL_VAI_TRO, "admin");

        // Cập nhật vai trò cho người dùng trong bảng DangNhapKhachHang
        db.update(
                TB_DANG_NHAP_KHACH_HANG,
                values,
                CL_TEN_DANGNHAP + " = ?",
                new String[]{tenDangNhap}
        );

        // Đóng kết nối database
        db.close();
    }
    public void setCustomerRoleForUser(String tenDangNhap) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Sử dụng ContentValues để chuẩn bị dữ liệu cần cập nhật
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.CL_VAI_TRO, "customer");

        // Cập nhật vai trò cho người dùng trong bảng DangNhapKhachHang
        db.update(
                TB_DANG_NHAP_KHACH_HANG,
                values,
                CL_TEN_DANGNHAP + " = ?",
                new String[]{tenDangNhap}
        );

        // Đóng kết nối database
        db.close();
    }
    //phuong thuc lay ra chuc vu cua nguoi dung
    @SuppressLint("Range")
    public String GetCLVaitro(String tendangnhap){
        String vaiTro = null;
        SQLiteDatabase db = this.getReadableDatabase();

        // Query để lấy chức vụ từ cơ sở dữ liệu
        String query = "SELECT " + CreateDatabase. CL_VAI_TRO  + " FROM " + CreateDatabase.TB_DANG_NHAP_KHACH_HANG +
                " WHERE " + CreateDatabase.CL_TEN_DANGNHAP + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{tendangnhap});

        // Kiểm tra xem có dữ liệu hay không
        if (cursor.moveToFirst()) {
            vaiTro = cursor.getString(cursor.getColumnIndex(CreateDatabase. CL_VAI_TRO ));
        }

        // Đóng cursor và database
        cursor.close();
        db.close();

        return vaiTro;
    }
}
