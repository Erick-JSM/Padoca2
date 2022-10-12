package com.erickjsm.padoca.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteProgram;
import android.util.Log;

import com.erickjsm.padoca.obj.Cliente;
import com.erickjsm.padoca.obj.ForneceProduto;
import com.erickjsm.padoca.obj.Fornecedor;
import com.erickjsm.padoca.obj.Funcionario;
import com.erickjsm.padoca.obj.Login;
import com.erickjsm.padoca.obj.Pedido;
import com.erickjsm.padoca.obj.Produto;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;

public class DAO extends SQLiteOpenHelper {

    public DAO(Context context) {
        super(context, "BD Padoca", null, 25);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Gerando tabelas do Banco de dados

        db.execSQL("CREATE TABLE Login(" +
                "login VARCHAR(10) NOT NULL," +
                "senha INTEGER NOT NULL," +
                "PRIMARY KEY(login));");
        db.execSQL("INSERT INTO Login VALUES('admin', '417864');");

        db.execSQL("CREATE TABLE Cliente(" +
                "pNome VARCHAR(20) NOT NULL," +
                "minimal CHAR(1)," +
                "uNome VARCHAR(20) NOT NULL," +
                "cpf CHAR(11)," +
                "endereco VARCHAR(60)," +
                "telefone CHAR(11) NOT NULL," +
                "dataNasc DATE," +
                "sexo CHAR(1) NOT NULL," +
                "divida DOUBLE DEFAULT 0," +
                "ultimoValorPago DOUBLE DEFAULT 0," +
                "PRIMARY KEY(cpf));");

        db.execSQL("CREATE TABLE Funcionario(" +
                "pNome VARCHAR(20) NOT NULL," +
                "minimal CHAR(1)," +
                "uNome VARCHAR(20) NOT NULL," +
                "cpf CHAR(11)," +
                "endereco VARCHAR(60)," +
                "telefone CHAR(11) NOT NULL," +
                "dataNasc DATE," +
                "sexo CHAR(1) NOT NULL," +
                "cargo VARCHAR(20) NOT NULL," +
                "salario DOUBLE DEFAULT 0," +
                "PRIMARY KEY(cpf));");

        db.execSQL("CREATE TABLE Produto(" +
                "nome VARCHAR(30) NOT NULL," +
                "codProduto INTEGER," +
                "qtdEstoque INTERGER DEFAULT 0," +
                "dataFabric DATE," +
                "dataVenc DATE," +
                "valor DOUBLE DEFAULT 0," +
                "PRIMARY KEY(codProduto));");

        db.execSQL("CREATE TABLE Fornecedor(" +
                "nome VARCHAR(20) NOT NULL," +
                "endereco VARCHAR(50)," +
                "telefone CHAR NOT NULL," +
                "email VARCHAR(50)," +
                "cnpj CHAR(11)," +
                "PRIMARY KEY(cnpj));");

        db.execSQL("CREATE TABLE Pedido(" +
                "codPedido IDENTITY," +
                "data DATE DEFAULT CURRENT_TIMESTAMP," +
                "qtdPedida INTEGER NOT NULL," +
                "fk_fCpf CHAR (11)," +
                "PRIMARY KEY(codPedido)," +

                "FOREIGN KEY (fk_fCpf) REFERENCES Funcionario (Cpf)" +
                "ON DELETE CASCADE          ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE Faz_pedido(" +
                "fk_cCpf CHAR(11)," +
                "fk_pCodPedido INTEGER," +

                "FOREIGN KEY (fk_cCpf) REFERENCES Cliente (Cpf)" +
                "ON DELETE CASCADE          ON UPDATE CASCADE," +
                "FOREIGN KEY (fk_pCodPedido) REFERENCES Pedido (CodPedido)" +
                "ON DELETE CASCADE         ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE Tem_produtos(" +
                "fk_pedidoCod INTEGER," +
                "fk_produtoCod INTEGER," +

                "FOREIGN KEY (fk_pedidoCod) REFERENCES Pedido (CodPedido)" +
                "ON DELETE CASCADE         ON UPDATE CASCADE," +
                "FOREIGN KEY (fk_produtoCod) REFERENCES Produto (CodProduto)" +
                "ON DELETE CASCADE         ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE Fornece(" +
                "fk_fCnpj CHAR," +
                "fk_codProduto INTEGER," +
                "data DATE NOT NULL," +
                "qtdFornecida INTEGER DEFAULT 0," +

                "FOREIGN KEY (fk_fCnpj) REFERENCES Fornecedor (Cnpj)" +
                "ON DELETE CASCADE         ON UPDATE CASCADE," +
                "FOREIGN KEY (fk_codProduto) REFERENCES Produto (CodProduto)" +
                "ON DELETE CASCADE         ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE Prepara(" +
                "fk_fCpf CHAR," +
                "fk_codProduto INTEGER," +
                "FOREIGN KEY (fk_fCpf) REFERENCES Funcionario (Cpf)" +
                "ON DELETE CASCADE         ON UPDATE CASCADE," +
                "FOREIGN KEY (fk_CodProduto) REFERENCES Produto (CodProduto)" +
                "ON DELETE CASCADE         ON UPDATE CASCADE)");

        //db.execSQL("Alter table Funcionario ADD FOREIGN KEY() references();");

        db.execSQL("INSERT INTO Cliente (Divida, UltimoValorPago, Cpf, Endereco, Telefone, PNome, UNome, Minimal, DataNasc, Sexo) VALUES ('2.50' , '2.50' , '11122233312' , 'Rua Celestial - 485' , '34552323' , 'Laura' , 'Pinheiro' , 'P' , '04/04/2004' , 'F')");
        db.execSQL("INSERT INTO Cliente (Divida, UltimoValorPago, Cpf, Endereco, Telefone, PNome, UNome, Minimal, DataNasc, Sexo) VALUES ('10.0' , '10.0' , '11122233322' , 'Rua Alameda - 235' , '35672323' , 'Kleber' , 'Carvalho' , 'C' , '04/05/1997' , 'M')");
        db.execSQL("INSERT INTO Cliente (Divida, UltimoValorPago, Cpf, Endereco, Telefone, PNome, UNome, Minimal, DataNasc, Sexo) VALUES ('6.50' , '6.50' , '11122233332' , 'Av. D.Pedro - 123' , '38905420' , 'Camila' , 'Jatobá' , 'J' , '05/02/2000' , 'F')");

        db.execSQL("INSERT INTO Funcionario (Cargo, Salario, Cpf, Endereco, Telefone, PNome, UNome, Minimal, DataNasc, Sexo) VALUES ('Padeiro' , '2200,00' , '22233344400' , 'Rua Alvarenga - 75' , '32343567' , 'Cecilia' , 'Felicius' , 'F' , '04/03/1990' , 'F')");
        db.execSQL("INSERT INTO Funcionario (Cargo, Salario, Cpf, Endereco, Telefone, PNome, UNome, Minimal, DataNasc, Sexo) VALUES ('Padeiro' , '1800,00' , '22233344401' , 'Rua Ladeiro - 578' , '32343567' , 'Arthur' , 'Tarlio' , 'T' , '04/04/2000' , 'M')");
        db.execSQL("INSERT INTO Funcionario (Cargo, Salario, Cpf, Endereco, Telefone, PNome, UNome, Minimal, DataNasc, Sexo) VALUES ('Caixa' , '1500,00' , '22233344402' , 'Rua Dadiario - 779' , '32343567' , 'Carlos' , 'Farista' , 'F' , '04/01/1998' , 'M')");
        db.execSQL("INSERT INTO Funcionario (Cargo, Salario, Cpf, Endereco, Telefone, PNome, UNome, Minimal, DataNasc, Sexo) VALUES ('Caixa' , '1500,00' , '22233344403' , 'Rua Ladeiro - 223' , '32343567' , 'Cecilia' , 'Silva' , 'S' , '04/07/2002' , 'F' )");

        db.execSQL("INSERT INTO Produto (CodProduto, Nome, QtdEstoque, dataFabric, dataVenc, Valor) VALUES('0101' , 'Pão de Queijo' , '20' , '09/10/2022' , '11/10/2022' , '2,50')");
        db.execSQL("INSERT INTO Produto (CodProduto, Nome, QtdEstoque, dataFabric, dataVenc, Valor) VALUES('0201' , 'Bolo' , '8' , '09/10/2022' , '15/10/2022' , '5,00')");
        db.execSQL("INSERT INTO Produto (CodProduto, Nome, QtdEstoque, dataFabric, dataVenc, Valor) VALUES('0301' , 'Pão Frances' , '30' , '09/10/2022' , '15/10/2022' , '0,50')");
        db.execSQL("INSERT INTO Produto (CodProduto, Nome, QtdEstoque, dataFabric, dataVenc, Valor) VALUES('0401' , 'Pão Doce' , '15' , '09/10/2022' , '18/10/2022' , '0,60')");
        db.execSQL("INSERT INTO Produto (CodProduto, Nome, QtdEstoque, dataFabric, dataVenc, Valor) VALUES('0501' , 'Café' , '8' , '09/10/2022' , '09/10/2022' , '1,00')");
        db.execSQL("INSERT INTO Produto (CodProduto, Nome, QtdEstoque, dataFabric, dataVenc, Valor) VALUES('0601' , 'Suco' , '10' , '09/10/2022' , '09/10/2022' , '1,50')");

        db.execSQL("INSERT INTO Fornecedor (Nome, Endereco, Telefone, Email, Cnpj) VALUES ('Fornitas' , 'Av. Carmila - 84' , '33452098' , 'fornitas@fornece.com' , '125')");
        db.execSQL("INSERT INTO Fornecedor (Nome, Endereco, Telefone, Email, Cnpj) VALUES ('AtacadãoDRegiã0' , 'Av. Carmila - 327' , '334557' , 'atka@fornece.com' , '135')");
        db.execSQL("INSERT INTO Fornecedor (Nome, Endereco, Telefone, Email, Cnpj) VALUES ('Poupas e Frutas' , 'Av. D.Predo - 240' , '37654822' , 'frutiferas@fornece.com' , '145')");

        db.execSQL("INSERT INTO Pedido (CodPedido, Data, QtdPedida, fk_fCpf) VALUES ('0001' , '09/10/20022' , '1' , '22233344403' )");
        db.execSQL("INSERT INTO Pedido (CodPedido, Data, QtdPedida, fk_fCpf) VALUES ('0002' , '09/10/2022' , '2' , '22233344402' )");
        db.execSQL("INSERT INTO Pedido (CodPedido, Data, QtdPedida, fk_fCpf) VALUES ('0003' , '09/10/2022' , '13' , '22233344403')");

        db.execSQL("INSERT INTO Faz_pedido (fk_cCpf, fk_pCodPedido) VALUES ('11122233312' , '0001')");
        db.execSQL("INSERT INTO Faz_pedido (fk_cCpf, fk_pCodPedido) VALUES ('11122233322' , '0002')");
        db.execSQL("INSERT INTO Faz_pedido (fk_cCpf, fk_pCodPedido) VALUES ('11122233332' , '0003')");

        db.execSQL("INSERT INTO Tem_produtos (fk_pedidoCod, fk_produtoCod) VALUES ('0001' , '0101')");
        db.execSQL("INSERT INTO Tem_produtos (fk_pedidoCod, fk_produtoCod) VALUES ('0002' , '0201')");
        db.execSQL("INSERT INTO Tem_produtos (fk_pedidoCod, fk_produtoCod) VALUES ('0003' , '0301')");

        db.execSQL("INSERT INTO Fornece (fk_fCnpj, fk_codProduto, Data, qtdFornecida) VALUES ('125' , '0101' , '07/10/2022' , '100')");
        db.execSQL("INSERT INTO Fornece (fk_fCnpj, fk_codProduto, Data, qtdFornecida) VALUES ('135' , '0201' , '07/10/2022' , '30')");
        db.execSQL("INSERT INTO Fornece (fk_fCnpj, fk_codProduto, Data, qtdFornecida) VALUES ('145' , '0601' , '07/10/2022' , '40')");
        db.execSQL("INSERT INTO Fornece (fk_fCnpj, fk_codProduto, Data, qtdFornecida) VALUES ('125' , '0301' , '07/10/2022' , '100')");
        db.execSQL("INSERT INTO Fornece (fk_fCnpj, fk_codProduto, Data, qtdFornecida) VALUES ('125' , '0401' , '07/10/2022' , '100')");
        db.execSQL("INSERT INTO Fornece (fk_fCnpj, fk_codProduto, Data, qtdFornecida) VALUES ('145' , '0501' , '07/10/2022' , '40')");

        db.execSQL("INSERT INTO Prepara (fk_fCpf, fk_CodProduto) VALUES ('22233344401' , '0101')");
        db.execSQL("INSERT INTO Prepara (fk_fCpf, fk_CodProduto) VALUES ('22233344401' , '0201')");
        db.execSQL("INSERT INTO Prepara (fk_fCpf, fk_CodProduto) VALUES ('22233344401' , '0301')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("PRAGMA foreign_key = OFF;");

        db.execSQL("DROP TABLE IF EXISTS Login");
        db.execSQL("DROP TABLE IF EXISTS Cliente");
        db.execSQL("DROP TABLE IF EXISTS Faz_pedido");
        db.execSQL("DROP TABLE IF EXISTS Tem_produtos");
        db.execSQL("DROP TABLE IF EXISTS Fornece");
        db.execSQL("DROP TABLE IF EXISTS Prepara");
        db.execSQL("DROP TABLE IF EXISTS Funcionario");
        db.execSQL("DROP TABLE IF EXISTS Produto");
        db.execSQL("DROP TABLE IF EXISTS Fornecedor");
        db.execSQL("DROP TABLE IF EXISTS Pedido");

        //db.execSQL("PRAGMA foreign_key = OFF;");

        onCreate(db);
    }
//------------------------------------------------------------ INSERCAO ------------------------------------------------------------

    public void insertCliente(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("pNome", cliente.getpNome());
        dados.put("minimal", cliente.getMinimal());
        dados.put("uNome", cliente.getuNome());
        dados.put("cpf", cliente.getCPF());
        dados.put("endereco", cliente.getEndereco());
        dados.put("telefone", cliente.getTelefone());
        dados.put("dataNasc", String.valueOf(cliente.getDataNasc()));
        dados.put("sexo", String.valueOf(cliente.getSexo()));
        dados.put("divida", cliente.getDivida());
        dados.put("ultimoValorPago", cliente.getValorPago());

        db.insert("Cliente", null, dados);
    }

    public void insertFuncionario(Funcionario funcionario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("pNome", funcionario.getpNome());
        dados.put("minimal", funcionario.getMinimal());
        dados.put("uNome", funcionario.getuNome());
        dados.put("cpf", funcionario.getCPF());
        dados.put("endereco", funcionario.getEndereco());
        dados.put("telefone", funcionario.getTelefone());
        dados.put("dataNasc", String.valueOf(funcionario.getDataNasc()));
        dados.put("sexo", String.valueOf(funcionario.getSexo()));
        dados.put("cargo", funcionario.getCargo());
        dados.put("salario", funcionario.getSalario());

        db.insert("Funcionario", null, dados);
    }

    public void insertProduto(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", produto.getNome());
        dados.put("codProduto", produto.getCodProduto());
        dados.put("qtdEstoque", produto.getQtdEstoque());
        dados.put("dataFabric", produto.getFabricacao());
        dados.put("dataVenc", produto.getTermino());
        dados.put("valor", produto.getValor());

        db.insert("Produto", null, dados);

    }

    public void insertPedido(Pedido pedido) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("codPedido", pedido.getCodPedido());
        dados.put("data", String.valueOf(pedido.getData()));
        dados.put("qtdPedida", pedido.getQtdPedida());
        dados.put("fk_fCpf", pedido.getCpfFuncionario());

        db.insert("Pedido", null, dados);
    }

    public void insertFornecedor(Fornecedor fornecedor) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", fornecedor.getNome());
        dados.put("endereco", fornecedor.getEndereco());
        dados.put("telefone", fornecedor.getTelefone());
        dados.put("email", fornecedor.getEmail());
        dados.put("cnpj", fornecedor.getCnpj());

        db.insert("Fornecedor", null, dados);
    }

    public void insertFaz_pedido(Cliente cliente, Pedido pedido) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("fk_cCpf", cliente.getCPF());
        dados.put("fk_pCodPedido", pedido.getCodPedido());

        db.insert("faz_pedido", null, dados);
    }

    public void inserTem_produtos(Pedido pedido, Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("produtoCod", produto.getCodProduto());
        dados.put("pedidoCod", pedido.getCodPedido());

        db.insert("tem_produtos", null, dados);
    }

    public void insertFornece(Fornecedor fornecedor, ForneceProduto forneceProduto, Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("fk_cnpj", fornecedor.getCnpj());
        dados.put("fk_codProduto", produto.getCodProduto());
        dados.put("data", String.valueOf(forneceProduto.getData()));
        dados.put("qtdFornecida", forneceProduto.getQtdFornecida());

        db.insert("Fornece", null, dados);
    }

    public void insertPrepara(Funcionario funcionario, Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("fk_fCpf", funcionario.getCPF());
        dados.put("fk_codProduto", produto.getCodProduto());

        db.insert("Prepara", null, dados);
    }


//------------------------------------------------------------ RETORNA UMA TABELA ------------------------------------------------------------
//------------------------------------------------------------ BUSCAS ------------------------------------------------------------

    public Login fazerLogin(Login login){
        SQLiteDatabase db = getReadableDatabase();
        String sql_login = "SELECT * FROM Login WHERE Login = "+"'"+login.getLogin()+"'"+" AND Senha = "+"'"+login.getSenha()+"'";
        Cursor c = db.rawQuery(sql_login, null);

        while(c.moveToNext()){
            Login lg = new Login();
            lg.setLogin(c.getString(Integer.parseInt("Login")));
            lg.setSenha(c.getString(Integer.parseInt("Senha")));

            return lg;
        }
        return null;
    }

    //=========================================================== Clientes ============================================================================
    // Busca Todos os clientes
    @SuppressLint("Range")
    public List<Cliente> buscaCliente(){
        String sql = "SELECT * FROM Cliente;" ;
        SQLiteDatabase db = getReadableDatabase();

        List<Cliente> clientes = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);

        while(c.moveToNext()){
            Cliente cliente = new Cliente();

            cliente.setpNome(c.getString(c.getColumnIndex("pNome")));
            cliente.setMinimal(c.getString(c.getColumnIndex("minimal")));
            cliente.setuNome(c.getString(c.getColumnIndex("uNome")));
            cliente.setCPF(c.getString(c.getColumnIndex("cpf")));
            cliente.setSexo(c.getString(c.getColumnIndex("sexo")));

            clientes.add(cliente);
        }
        for(Cliente clienteBuscado: clientes){
            Log.d("CPFNoDAO", clienteBuscado.getCPF());
        }
        return clientes;
    }
    // Busca um unico cliente
    @SuppressLint("Range")
    public Cliente buscaUmCliente(String key){
        String sql = "SELECT * FROM Cliente WHERE cpf = "+"'"+key+"'"+";";
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);

        if(c.moveToNext()) {
            Cliente cliente = new Cliente();

            cliente.setpNome(c.getString(c.getColumnIndex("pNome")));
            cliente.setMinimal(c.getString(c.getColumnIndex("minimal")));
            cliente.setuNome(c.getString(c.getColumnIndex("uNome")));
            cliente.setCPF(c.getString(c.getColumnIndex("cpf")));
            cliente.setSexo(c.getString(c.getColumnIndex("sexo")));
            cliente.setDataNasc(c.getString(c.getColumnIndex("dataNasc")));
            cliente.setTelefone(c.getString(c.getColumnIndex("telefone")));
            cliente.setEndereco(c.getString(c.getColumnIndex("endereco")));
            cliente.setDivida(c.getString(c.getColumnIndex("divida")));
            cliente.setValorPago(c.getString(c.getColumnIndex("ultimoValorPago")));

            return cliente;
        }
        else {
            return null;
        }
    }
    // Exclui cliente
    public void apagaPessoa(String CPF){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM Cliente WHERE cpf = "+"'"+CPF+"';";
        db.execSQL(sql);

    }

    //=========================================================== Funcionario ============================================================================
    // Busca Todos os funcioanrios
    @SuppressLint("Range")
    public List<Funcionario> buscaFuncionario(){
        String sql = "SELECT * FROM Funcionario;" ;
        SQLiteDatabase db = getReadableDatabase();

        List<Funcionario> funcionarios = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);

        while(c.moveToNext()){
            Funcionario funcionario = new Funcionario();

            funcionario.setpNome(c.getString(c.getColumnIndex("pNome")));
            funcionario.setMinimal(c.getString(c.getColumnIndex("minimal")));
            funcionario.setuNome(c.getString(c.getColumnIndex("uNome")));
            funcionario.setCPF(c.getString(c.getColumnIndex("cpf")));
            funcionario.setSexo(c.getString(c.getColumnIndex("sexo")));
            funcionario.setCargo(c.getString(c.getColumnIndex("cargo")));


            funcionarios.add(funcionario);
        }
        for(Funcionario funcionarioBuscado: funcionarios){
            Log.d("CPFNoDAO", funcionarioBuscado.getCPF());
        }
        return funcionarios;
    }
    // Busca um unico funcionario
    @SuppressLint("Range")
    public Funcionario buscaUmFuncionario(String key) {
        String sql = "SELECT * FROM Funcionario WHERE cpf = " + "'" + key + "'" + ";";
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToNext()) {
            Funcionario funcionario = new Funcionario();

            funcionario.setpNome(c.getString(c.getColumnIndex("pNome")));
            funcionario.setMinimal(c.getString(c.getColumnIndex("minimal")));
            funcionario.setuNome(c.getString(c.getColumnIndex("uNome")));
            funcionario.setCPF(c.getString(c.getColumnIndex("cpf")));
            funcionario.setSexo(c.getString(c.getColumnIndex("sexo")));
            funcionario.setDataNasc(c.getString(c.getColumnIndex("dataNasc")));
            funcionario.setTelefone(c.getString(c.getColumnIndex("telefone")));
            funcionario.setEndereco(c.getString(c.getColumnIndex("endereco")));
            funcionario.setSalario(c.getString(c.getColumnIndex("salario")));
            funcionario.setCargo(c.getString(c.getColumnIndex("cargo")));

            return funcionario;
        } else {
            return null;
        }
    }
    // Exclui funcionario
    public void apagaFuncionario(String CPF){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM Funcionario WHERE cpf = "+"'"+CPF+"';";
        db.execSQL(sql);

    }

    //=========================================================== Fornecedor ============================================================================
    // Busca Todos os Fornecedor
    @SuppressLint("Range")
    public List<Fornecedor> buscaFornecedor(){
        String sql = "SELECT * FROM Fornecedor;" ;
        SQLiteDatabase db = getReadableDatabase();

        List<Fornecedor> fornecedores = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);

        while(c.moveToNext()){
            Fornecedor fornecedor = new Fornecedor();

            fornecedor.setNome(c.getString(c.getColumnIndex("nome")));
            fornecedor.setCnpj(c.getString(c.getColumnIndex("cnpj")));

            fornecedores.add(fornecedor);
        }
        for(Fornecedor funcioanrioBuscado: fornecedores){
            Log.d("CNPJNoDAO", funcioanrioBuscado.getCnpj());
        }
        return fornecedores;
    }
    // Busca um unico fornecedor
    @SuppressLint("Range")
    public Fornecedor buscaUmFornecedor(String key) {
        String sql = "SELECT * FROM Fornecedor WHERE cnpj = " + "'" + key + "'" + ";";
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToNext()) {
            Fornecedor fornecedor = new Fornecedor();

            fornecedor.setNome(c.getString(c.getColumnIndex("nome")));
            fornecedor.setEmail(c.getString(c.getColumnIndex("email")));
            fornecedor.setEndereco(c.getString(c.getColumnIndex("endereco")));
            fornecedor.setTelefone(c.getString(c.getColumnIndex("telefone")));
            fornecedor.setCnpj(c.getString(c.getColumnIndex("cnpj")));

            return fornecedor;
        } else {
            return null;
        }
    }
    // Exclui fornecedor
    public void apagaFornecedor(String CNPJ){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM Fornecedor WHERE cnpj = "+"'"+CNPJ+"';";
        db.execSQL(sql);
    }

    //=========================================================== Produtos ============================================================================
    // Busca Todos os produtos
    @SuppressLint("Range")
    public List<Produto> buscaProduto(){
        String sql = "SELECT * FROM Produto;" ;
        SQLiteDatabase db = getReadableDatabase();

        List<Produto> produtos = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);

        while(c.moveToNext()){
            Produto produto = new Produto();
            produto.setNome(c.getString(c.getColumnIndex("nome")));
            produto.setCodProduto(c.getString(c.getColumnIndex("codProduto")));

            produtos.add((produto));
        }
        for(Produto produtoBuscado: produtos){
            Log.d("NomeNoDAO", produtoBuscado.getNome());
        }
        return produtos;
    }
    // Busca um unico produto
    @SuppressLint("Range")
    public Produto buscaUmProduto(String key) {
        String sql = "SELECT * FROM Produto WHERE codProduto = " + "'" + key + "'" + ";";

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        c.moveToNext();
        Produto produto = new Produto();
        System.out.println(produto.getCodProduto());

        produto.setCodProduto(c.getString(c.getColumnIndex("codProduto")));
        produto.setQtdEstoque(c.getString(c.getColumnIndex("qtdEstoque")));
        produto.setFabricacao(c.getString(c.getColumnIndex("dataFabric")));
        produto.setTermino(c.getString(c.getColumnIndex("dataVenc")));
        produto.setValor(c.getString(c.getColumnIndex("valor")));
        produto.setNome(c.getString(c.getColumnIndex("nome")));

        return produto;
    }

    // Exclui Produto
    public void apagaProduto(String codProduto){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM Produto WHERE codProduto = "+"'"+codProduto+"';";
        db.execSQL(sql);

    }

    //=========================================================== Vendas ============================================================================
    // Busca Todos os pedido
    @SuppressLint("Range")
    public List<Pedido> buscaPedido(){
        String sql = "SELECT * FROM Pedido;" ;
        SQLiteDatabase db = getReadableDatabase();

        List<Pedido> pedidos = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);

        while(c.moveToNext()){
            Pedido pedido= new Pedido();
            pedido.setCodPedido(c.getString(c.getColumnIndex("codPedido")));
            pedido.setData(c.getString(c.getColumnIndex("data")));

            pedidos.add((pedido));
        }
        for(Pedido pedidoBuscado: pedidos){
            Log.d("NomeNoDAO", String.valueOf(pedidoBuscado.getCodPedido()));
        }
        return pedidos;
    }
    // Busca um unico pedido
    @SuppressLint("Range")
    public Pedido buscaUmPedido(String key) {
        String sql = "SELECT * FROM Pedido WHERE codPedido = " + "'" + key + "'" + ";";
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToNext()) {
            Pedido pedido = new Pedido();

            pedido.setCodPedido(c.getString(c.getColumnIndex("codPedido")));
            pedido.setQtdPedida(c.getString(c.getColumnIndex("qtdPedida")));
            pedido.setCpfFuncionario(c.getString(c.getColumnIndex("fk_fCpf")));
            pedido.setData(c.getString(c.getColumnIndex("data")));

            return pedido;
        } else {
            return null;
        }
    }
    // Exclui Pedido
    public void apagaPedido(String codPedido){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM Pedido WHERE codPedido = "+"'"+codPedido+"';";
        db.execSQL(sql);
    }

    // Atualiza divida

    public void atualizaDivida(Double divida, String cpf, Double op){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "UPDATE Cliente Set divida = " + "'" + divida + "'" + " WHERE cpf = " + "'" + cpf + "';";
        db.execSQL(sql);

        if(!(op == 0.0)){
            sql = "UPDATE Cliente Set ultimoValorPago = " + "'" + op + "'" + " WHERE cpf = " + "'" + cpf + "';";
            db.execSQL(sql);
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_key = ON;");
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }
}







