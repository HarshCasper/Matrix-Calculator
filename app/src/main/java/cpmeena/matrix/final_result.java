package cpmeena.matrix;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * Created by CPMeena on 07-01-2018.
 */

public class final_result extends Activity {

    private matrix firstMatrix,secondMatrix,tempMatrix;

    boolean secondMatrixExistance;

    private int buttonClick;

    private TextView textView16;
    private Button Matrixbutton,homebutton0,homebutton1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix);

        buttonClick = getIntent().getIntExtra("key1",0);        //which button is pressed in previous activity

        secondMatrixExistance = defaultSecondMatrixExistance(); // we have to read one or two matrix

        /*for single matrix the text display : Enter the Matrix
        for double matrix the text display :
            for first matrix : Enter First Matrix
            for second matrix : Enter Second Matrix
        */
        textView16 = (TextView) findViewById(R.id.textView16);
        textView16.setText(settextview16String());

        Matrixbutton =(Button) findViewById(R.id.MatrixButton); // user had input data so done

        tempMatrix = new matrix();
        onClickListener();                  //what to do when user press done(Matrixbutton)
    }


    private void actionAccordingPreviousButton(){
        if(isSecondMatrixExist()){
            textView16.setText("Enter Second Matrix");
            firstMatrix = new matrix(tempMatrix);
            changeSecondMatrixExistance(false);
         //   Toast.makeText(this, "Fill Second Matrix", Toast.LENGTH_SHORT).show();
        }
        else {
            getMatrix();
            setLayout();
            if (buttonClick == 0) {
                fillSystemResult(firstMatrix.trace());
                onClickListener1();
            }
            if (buttonClick == 1) {
                if (firstMatrix.determinant() != 0) {
                    fillSystemMatrix(firstMatrix.inverse());
                    Log.d("prkash","inverse");
                    onClickListener2();
                } else{
                    answerNotExit();
                    onClickListener1();
                }
            }
            if (buttonClick == 2) {
                fillSystemResult(firstMatrix.determinant());
                onClickListener1();
            }
            if (buttonClick == 3) {
                fillSystemMatrix(firstMatrix.addition(secondMatrix));
                onClickListener2();
            }
            if (buttonClick == 4) {
                fillSystemMatrix(firstMatrix.subtraction(secondMatrix));
                onClickListener2();
            }
            if (buttonClick == 5) {
                fillSystemMatrix(firstMatrix.multiplication(secondMatrix));
                onClickListener2();
            }
            if (buttonClick == 6) {
                if (secondMatrix.determinant() != 0) {
                    fillSystemMatrix(firstMatrix.division(secondMatrix));
                    onClickListener2();
                } else {
                    answerNotExit();
                    onClickListener1();
                }
            }
            if (buttonClick == 7) {
                fillSystemMatrix(firstMatrix.transpose());
                onClickListener2();
            }
            if (buttonClick == 8) {
                fillSystemMatrix(firstMatrix.adjoint());
                onClickListener2();
            }
            if (buttonClick == 9) {
                fillSystemMatrix(firstMatrix.minor());
                onClickListener2();
            }
            if (buttonClick == 10) {
                if (firstMatrix.determinant() != 0) {
                    fillSystemMatrix(firstMatrix.inverse().multiplication(secondMatrix));
                    onClickListener2();
                } else {
                    answerNotExit();
                    onClickListener1();
                }
            }
            if (buttonClick == 11) {
                if (firstMatrix.determinant() != 0 && secondMatrix.determinant() != 0) {
                    fillSystemMatrix(firstMatrix.inverse().division(secondMatrix));
                    onClickListener2();
                } else {
                    answerNotExit();
                    onClickListener1();
                }
            }
            if (buttonClick == 12) {
                tempMatrix = firstMatrix.multiplication(secondMatrix);
                if (tempMatrix.determinant() != 0) {
                    fillSystemMatrix(tempMatrix.inverse());
                    onClickListener2();
                } else {
                    answerNotExit();
                    onClickListener1();
                }
            }
        }
    }
    private String operation(){
        switch (buttonClick){
            case 0: return "Trace";
            case 1: return "Inverse";
            case 2: return "Determinant";
            case 3: return "Addition";
            case 4: return "Subtraction";
            case 5: return "Multiplication";
            case 6: return "Result";
            case 7: return "Transpose";
            case 8: return "Adjoint";
            case 9: return "Minor";
            case 10: return "Result";
            case 11: return "Result";
            case 12: return "Result";
            default:return "Matrix";
        }
    }
    private String settextview16String(){
        if(secondMatrixExistance)
            return "Enter First Matrix";
        else
            return "Enter The Matrix";
    }

    private void getSystemMatrix(){

        EditText[][] ed = new EditText[3][3];

        ed[0][0] = (EditText) findViewById(R.id.editText0);
        ed[0][1] = (EditText) findViewById(R.id.editText1);
        ed[0][2] = (EditText) findViewById(R.id.editText2);
        ed[1][0] = (EditText) findViewById(R.id.editText3);
        ed[1][1] = (EditText) findViewById(R.id.editText4);
        ed[1][2] = (EditText) findViewById(R.id.editText5);
        ed[2][0] = (EditText) findViewById(R.id.editText6);
        ed[2][1] = (EditText) findViewById(R.id.editText7);
        ed[2][2] = (EditText) findViewById(R.id.editText8);

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
                String s = ed[i][j].getText().toString();
                if(s.isEmpty()||s=="."||s=="-"||s==null)
                    tempMatrix.changeElement(i,j,0);
                else
                    tempMatrix.changeElement(i,j,Double.parseDouble(s));
                ed[i][j].setText(null);
            }
    }
    private void fillSystemMatrix(matrix temp){

        TextView textview15 = (TextView) findViewById(R.id.textView15);
        textview15.setText("The "+operation()+" of matrices");

        TextView[][] textViews = new TextView[3][3];

        textViews[0][0] = (TextView) findViewById(R.id.textview5);
        textViews[0][1] = (TextView) findViewById(R.id.textview6);
        textViews[0][2] = (TextView) findViewById(R.id.textview7);
        textViews[1][0] = (TextView) findViewById(R.id.textview8);
        textViews[1][1] = (TextView) findViewById(R.id.textview9);
        textViews[1][2] = (TextView) findViewById(R.id.textview10);
        textViews[2][0] = (TextView) findViewById(R.id.textview11);
        textViews[2][1] = (TextView) findViewById(R.id.textview12);
        textViews[2][2] = (TextView) findViewById(R.id.textview13);

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
                double d = temp.getElement(i,j);
                double dd = (int)d;
                if(d==dd)
                    textViews[i][j].setText(Integer.toString((int)d));
                else
                    textViews[i][j].setText(Double.toString(d));
            }
    }
    private void fillSystemResult(double d){

        TextView textview3 = (TextView) findViewById(R.id.textView16);
        TextView textview4 = (TextView) findViewById(R.id.textView4);

        textview3.setText("The "+operation()+" of Matrix :");

        double dd = (int)d;
        if(d==dd)
            textview4.setText(Integer.toString((int)d));
        else
            textview4.setText(Double.toString(d));
    }

    private void getMatrix(){
        if(defaultSecondMatrixExistance())
            secondMatrix = new matrix(tempMatrix);
        else
            firstMatrix = new matrix(tempMatrix);
    }

    private void answerNotExit(){
        setContentView(R.layout.final_result);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setText(operation()+" doesn't exit.");
    }

    private int findLayout(){
        switch (buttonClick){
            case 0:case 2: return 0;
            default: return 1;
        }
    }
    private void setLayout(){
        if(findLayout()==0)
            setContentView(R.layout.final_result);
        else
            setContentView(R.layout.final_matrix);
    }

    private boolean isSecondMatrixExist(){
        return secondMatrixExistance;
    }
    private boolean defaultSecondMatrixExistance(){
        switch (buttonClick){
            case 3:case 4:case 5:case 6:case 10:case 11:case 12: return true;
            default: return false;
        }
    }
    private void changeSecondMatrixExistance(boolean flag){
        secondMatrixExistance = flag;
    }

    private void onClickListener(){
        Matrixbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSystemMatrix();
                actionAccordingPreviousButton();
            }});

    }
    private void onClickListener1(){
        homebutton0 = (Button) findViewById(R.id.homeButton0);
        homebutton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void onClickListener2(){
        homebutton1 = (Button) findViewById(R.id.homeButton1);
        homebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
