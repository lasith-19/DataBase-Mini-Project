package lk.ac.mrt.cse.dbs.simpleexpensemanager.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

public class DatabaseIni extends SQLiteOpenHelper {

    public static final String Account_table ="ACCOUNT_DET";
    public static final String ExpenseType_table="ExpenseType_DET";
    public static final String Transaction_table="TRANSACTION_DET";
    public static final String Account_number="accountNo";
    public static final String Bank_name="bankName";
    public static final String Holder_name="accountHolderName";
    public static final String Type_ID="Type_ID";
    public static final String Type_name="Type";
    public static final String Date="Date";
    public static final String TAccount_num="Account_number";
    public static final String Expense_type="Expense_type";
    public static final String Amount="Amount";

    public DatabaseIni(@Nullable Context context) {
        super(context, "190483N", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TableAccount = "CREATE TABLE "+Account_table+"("+Account_number+" TEXT PRIMARY KEY,"+Bank_name+" TEXT,"+Holder_name+" TEXT)";
        String TableExpenseType = "CREATE TABLE "+ExpenseType_table+"("+Type_ID+" INTEGER PRIMARY KEY,"+Type_name+" TEXT)";
        String TableTransaction ="CREATE TABLE "+Transaction_table+"("+Date+" TEXT,"+TAccount_num+" TEXT,"+Expense_type+" INTEGER,"+Amount+" REAL)";


        sqLiteDatabase.execSQL(TableAccount);
        sqLiteDatabase.execSQL(TableExpenseType);
        sqLiteDatabase.execSQL(TableTransaction);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean add_Account_Record(Account ac){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Con_vals = new ContentValues();

        Con_vals.put(Account_number, ac.getAccountNo());
        Con_vals.put(Bank_name,ac.getBankName());
        Con_vals.put(Holder_name,ac.getAccountHolderName());

        long insert = db.insert(Account_table, null, Con_vals);

        if(insert==-1){
            return false;
        }else{
            return true;
        }

    }
}
