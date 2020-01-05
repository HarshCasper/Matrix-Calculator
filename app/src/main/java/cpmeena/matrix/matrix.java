package cpmeena.matrix;

import android.util.Log;

/**
 * Created by CPMeena on 07-01-2018.
 */

public class matrix {
    private double[][] r;

    public matrix(){
        r = new double[3][3];
        this.fill_matrix();
    }
    public matrix(double[][] p) {
        r = new double[3][3];
        this.copy_matrix(p);
    }
    public matrix(matrix t){
        r = new double[3][3];
        copy_matrix(t.r);
    }

    public double getElement(int i, int j){
        return r[i][j];
    }
    public void changeElement(int i, int j, double n){
        this.r[i][j] = n;
    }

    public void copy_matrix(double[][] p){
        for (int i = 0; i < 3; i++) {
            System.arraycopy(p[i], 0, this.r[i], 0, 3);
        }
    }
    public void copy_matrix(double[] p){
        r[0][0] = p[0];
        r[0][1] = p[1];
        r[0][2] = p[2];
        r[1][0] = p[3];
        r[1][1] = p[4];
        r[1][2] = p[5];
        r[2][0] = p[6];
        r[2][1] = p[7];
        r[2][2] = p[8];
    }
    public void copy_by_row(int n, double[] p){
        System.arraycopy(p, 0, this.r[n], 0, 3);
    }

    private void fill_matrix(){
        for(int i=0;i<3;i++)for(int j=0;j<3;j++)
            this.r[i][j] = 0;
    }

    public double[][] return_matrix(){
        return r;
    }







    public  matrix addition(matrix p){
        matrix q = new matrix();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                q.r[i][j] = this.r[i][j]+ p.r[i][j];
        return q;
    }

    public  matrix subtraction(matrix p){
        matrix q = new matrix();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                q.r[i][j] = this.r[i][j]- p.r[i][j];
        return q;
    }

    public matrix multiplication(matrix p){
        matrix q = new matrix();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                q.r[i][j] = this.r[i][0]*p.r[0][j] + this.r[i][1]*p.r[1][j] + this.r[i][2]*p.r[2][j];
            }
        }
        return q;
    }
    public matrix multiplication(double p){
        matrix temp = new matrix();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                temp.r[i][j] = this.r[i][j]*p;
        return temp;
    }

    public matrix division(matrix p){
        matrix q = new matrix(this.multiplication(p.inverse()));
        return q;
    }
    public matrix division(double p){
        matrix temp = new matrix();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                temp.r[i][j] = (this.r[i][j])/p;
        return temp;
    }

    public double determinant(){
            return this.r[0][0]*(this.r[1][1]*this.r[2][2] - this.r[1][2]*this.r[2][1]) - this.r[0][1]*(this.r[1][0]*this.r[2][2] - this.r[1][2]*this.r[2][0]) + this.r[0][2]*(this.r[1][0]*this.r[2][1] - this.r[1][1]*this.r[0][2]);
    }

    public double trace(){
        return this.r[0][0] + this.r[1][1] + this.r[2][2];
    }

    public matrix transpose(){
        matrix temp = new matrix();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                temp.r[i][j] = this.r[j][i];
        return temp;
    }

    private double minorElement(int p,int q){
        double a=0,b=0;
        int c=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if (i != p && j != q) {
                    switch (c){
                        case 3: a = a*r[i][j];a=a-b; break;
                        case 2: b = b*r[i][j]; c++; break;
                        case 1: b = r[i][j]; c++; break;
                        case 0: a = r[i][j]; c++; break;
                    }
                }
            }
        }
        Log.d("prakash",Double.toString(a));
        return a;
    }

    public matrix minor(){
        matrix temp = new matrix();
        for(int i = 0;i<3;i++)
            for(int j=0;j<3;j++) {
                temp.r[i][j] = this.minorElement(i, j);
                Log.d("prakashminor", Double.toString(temp.r[i][j]));
            }
        return temp;
    }

    public matrix cofactor(){
        matrix temp = new matrix(this.minor());
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if((i+j)%2==1)
                    temp.r[i][j] = -1*temp.r[i][j];
        return temp;
    }

    public matrix adjoint(){
        return this.cofactor();
    }

    public matrix inverse(){
        matrix temp = new matrix();
        double det = this.determinant();
        if(det!=0)
            temp = this.cofactor().transpose().division(det);
        return temp;
    }




}
