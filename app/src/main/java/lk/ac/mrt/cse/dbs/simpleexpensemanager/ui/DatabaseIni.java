package lk.ac.mrt.cse.dbs.simpleexpensemanager.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
    public static final String Balance="Balance";


    public DatabaseIni(@Nullable Context context) {
        super(context, "190483N", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TableAccount = "CREATE TABLE "+Account_table+"("+Account_number+" TEXT PRIMARY KEY,"+Bank_name+" TEXT,"+Holder_name+" TEXT,"+Balance+" REAL)";
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
        Con_vals.put(Balance,ac.getBalance());

        long insert = db.insert(Account_table, null, Con_vals);

        if(insert==-1){
            return false;
        }else{
            return true;
        }

    }

    public List<String> get_account_numbers(){
        ArrayList<String> returnList = new ArrayList<>();

        String Accounts_list_query = "SELECT "+Account_number+" FROM "+Account_table;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cur = db.rawQuery(Accounts_list_query,null);
//        Cursor cur =db.query(Account_table,new String[] {Account_number},null,
//                null,null,null,null,null);

        if(cur.moveToFirst()) {
            do {
                //String accountNO = cur.getString(0);
                returnList.add(cur.getString(0));
            } while (cur.moveToNext());
        }

        cur.close();
        db.close();
        return returnList;
    }

    public List<Account> get_accounts(){
        ArrayList<Account> returnList= new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        String Accounts_query = "SELECT * FROM "+Account_table;
        SQLiteDatabase db1=this.getReadableDatabase();
        Cursor cur = db.rawQuery(Accounts_query,null);

        if(cur.moveToFirst()){
            do {
                String AccountNum = cur.getString(0);
                String BankName = cur.getString(1);
                String HolderName = cur.getString(2);
                double Bal = cur.getDouble(3);

                Account retAccount = new Account(AccountNum,BankName,HolderName,Bal);
                returnList.add(retAccount);
            }while(cur.moveToNext());
        }

        cur.close();
        db1.close();

        return returnList;
    }
}
