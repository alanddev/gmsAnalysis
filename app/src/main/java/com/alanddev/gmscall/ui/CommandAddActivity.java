package com.alanddev.gmscall.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alanddev.gmscall.R;
import com.alanddev.gmscall.controller.CommandController;
import com.alanddev.gmscall.helper.MwSQLiteHelper;
import com.alanddev.gmscall.model.Command;
import com.alanddev.gmscall.util.Constant;
import com.alanddev.gmscall.util.Utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

public class CommandAddActivity extends AppCompatActivity {

    private EditText edtserver;
    private Spinner spincmd;
    private EditText edttimetest;
    private EditText edtstream;
    private EditText edtnumber;
    private EditText edttimenext;
    private EditText edtuser;
    private EditText edtpass;
    private EditText edtstatus;
    private CommandController commandController;
    private Command command;

    private final int CREATE = 0;
    private final int EDIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtserver = (EditText)findViewById(R.id.edtserver);
        spincmd = (Spinner)findViewById(R.id.spincmd);
        edttimetest = (EditText)findViewById(R.id.edttimetest);
        edtstream = (EditText)findViewById(R.id.edtstream);
        edtnumber = (EditText)findViewById(R.id.edtnumber);
        edttimenext = (EditText)findViewById(R.id.edttimenext);
        edtuser = (EditText)findViewById(R.id.edtuser);
        edtpass = (EditText)findViewById(R.id.edtpass);
        edtstatus = (EditText)findViewById(R.id.edtstatus);

        commandController = new CommandController(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.command_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spincmd.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        long cmdid=0;
        if(bundle!=null){
            cmdid = bundle.getLong(MwSQLiteHelper.COLUMN_COMMAND_ID, 0);

        }


        if(cmdid==0){
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_command_add));
        }else{
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_command_update));
            commandController.open();
            command = commandController.getCommandById(cmdid);
            commandController.close();
            edtserver.setText(command.getServer());
            spincmd.setSelection(adapter.getPosition(command.getCmd()));
            edttimetest.setText(command.getTimeTest());
            edtstream.setText(command.getStream());
            edtnumber.setText(command.getNumber());
            edttimenext.setText(command.getTimeToNext());
            edtuser.setText(command.getUser());
            edtpass.setText(command.getPass());
            edtstatus.setText(command.getStatus());

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(command==null) {
            getMenuInflater().inflate(R.menu.menu_add, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_trans_detail, menu);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        if (id == R.id.save) {
            saveCommand(CREATE);
        }

        if (id == R.id.action_delete) {
            openConfirmDialog();
        }

        if (id == R.id.action_edit) {
            saveCommand(EDIT);
        }
        return super.onOptionsItemSelected(item);
    }


    public void openConfirmDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.dialog_confirm_delete_trans));

        alertDialogBuilder.setPositiveButton(getString(R.string.dialog_yes_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                delete(command.getId());
                setResult(Constant.COMMAND_ADD_RESULT, new Intent());
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton(getString(R.string.dialog_no_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //don't do anything
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void delete(long cmdId){
        commandController.open();
        commandController.delete(cmdId);
        commandController.close();
    }


    private void saveCommand(int type) {

        commandController.open();
        Command newcommand = new Command();
        newcommand.setServer(edtserver.getText().toString());
        newcommand.setCmd(String.valueOf(spincmd.getSelectedItem()));
        newcommand.setTimeTest(edttimetest.getText().toString());
        newcommand.setStream(edtstream.getText().toString());
        newcommand.setTimeToNext(edttimenext.getText().toString());
        newcommand.setNumber(edtnumber.getText().toString());
        newcommand.setUser(edtuser.getText().toString());
        newcommand.setPass(edtpass.getText().toString());
        newcommand.setStatus(edtstatus.getText().toString());
        if (type == CREATE) {
            commandController.createCommand(newcommand);
        } else if (type == EDIT) {
            newcommand.setId(command.getId());
            commandController.updateCommand(newcommand);
        }
        commandController.close();
        setResult(Constant.COMMAND_ADD_RESULT, new Intent());
        finish();
    }

}
