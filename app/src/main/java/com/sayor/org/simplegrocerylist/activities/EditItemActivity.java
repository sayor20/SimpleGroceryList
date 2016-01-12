package com.sayor.org.simplegrocerylist.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sayor.org.simplegrocerylist.R;
import com.sayor.org.simplegrocerylist.models.Grocery;
import com.sayor.org.simplegrocerylist.models.GroceryList;

public class EditItemActivity extends AppCompatActivity {

    EditText edtName, edtDesc, edtQty;
    CheckBox cbPurchase;
    int pos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        edtName = (EditText) findViewById(R.id.edtName);
        edtDesc = (EditText) findViewById(R.id.edtDesc);
        edtQty = (EditText) findViewById(R.id.edtQty);
        cbPurchase = (CheckBox) findViewById(R.id.cbPurchase);

        Intent i = getIntent();
        pos = i.getIntExtra("pos", -1);

        GroceryList groceryList = GroceryList.get(this);
        Grocery grocery = groceryList.getmGrocery(pos);

        edtName.setText(grocery.getName());
        edtDesc.setText(grocery.getDesc());
        edtQty.setText(grocery.getQty()==-1?"":grocery.getQty()+"");
        cbPurchase.setChecked(grocery.isDone());

    }

    // FAB events

    public void onSave(View v){
        GroceryList groceryList = GroceryList.get(this);
        groceryList.removeGrocery(pos);

        //changes to singleton class
        groceryList.addGrocery(pos, new Grocery(edtName.getText().toString(), edtDesc.getText().toString(),
                Integer.parseInt(edtQty.getText().toString().length()>0?edtQty.getText().toString():"-1"), cbPurchase.isChecked()));
        Toast.makeText(this, "Item have been modified.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onDelete(View v){
        final GroceryList groceryList = GroceryList.get(getParent());

        new AlertDialog.Builder(this).setTitle("Delete Items")
                .setMessage("Do you want to delete this item?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //changes to singleton class
                        groceryList.removeGrocery(pos);
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create().show();

    }
}
