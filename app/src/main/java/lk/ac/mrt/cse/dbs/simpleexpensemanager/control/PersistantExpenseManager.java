package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistantAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistantTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.ui.DatabaseIni;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.ui.MainActivity;

public class PersistantExpenseManager extends ExpenseManager{

    private Context con;
    public PersistantExpenseManager(Context Con1) throws ExpenseManagerException {

        this.con=Con1;
        this.setup();
    }


    @Override
    public void setup() throws ExpenseManagerException {

        DatabaseIni DB=new DatabaseIni(con);
        TransactionDAO PersisTransactionDAO = new PersistantTransactionDAO(DB);
        setTransactionsDAO(PersisTransactionDAO);

        AccountDAO PersisAccountDAO = new PersistantAccountDAO(DB);
        setAccountsDAO(PersisAccountDAO);
    }
}
