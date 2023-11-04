grammar MiniJava;


program : importStatement* mainClass (classDeclaration)* EOF ;

importStatement : 'import' qualifiedName ';' ;

qualifiedName : IDENTIFIER ('.' IDENTIFIER)* ;

mainClass : 'public'? 'class' IDENTIFIER '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' IDENTIFIER ')' '{' (statement)* '}' '}' ;

classDeclaration : 'class' IDENTIFIER ('extends' IDENTIFIER)? '{' (varDeclaration)* (methodDeclaration)* '}' ;

varDeclaration : type IDENTIFIER ('=' expression | '=' arrayInitializer)? ';' ;

methodDeclaration : 'public' type IDENTIFIER '(' (type IDENTIFIER (',' type IDENTIFIER)*)? ')' '{' (varDeclaration)* (statement)* 'return' expression ';' '}' ;

type : 'int' | '[' ']' | 'boolean' | 'String' | IDENTIFIER | 'int' '[' ']' | 'String' '[' ']' ;

arrayInitializer : '{' (expression (',' expression)*)? '}' ;

localVarDeclaration : type IDENTIFIER ('=' expression)? ';'? ;

forStatement : 'for' '(' forInit ';' expression ';' forUpdate ')' statement ;

forInit : localVarDeclaration
       | IDENTIFIER '=' expression
       | ;  // Empty initialization

forUpdate : IDENTIFIER '++'
          | IDENTIFIER '--'
          | IDENTIFIER '+=' expression
          | IDENTIFIER '-=' expression
          | IDENTIFIER '=' expression
          ;

statement : '{' (statement)* '}'
          | IF '(' expression ')' statement
          | IF '(' expression ')' statement ELSE statement
          | WHILE '(' expression ')' statement
          | 'System.out.println' '(' '"' expression '"' ')' ';'
          | 'System.out.println' '(' expression ')' ';'
          | IDENTIFIER '=' expression ';'
          | IDENTIFIER '++' ';'
          | IDENTIFIER '--' ';'
          | IDENTIFIER '[' expression ']' '=' expression ';'
          | localVarDeclaration
          | forStatement
          | IDENTIFIER '[' expression ']' '=' expression ';'
          | varDeclaration
          ;

expression : expression ('&&' | '<' | '+' | '>' | '==') expression
           | expression '[' expression ']'
           | expression '.' 'length'
           | expression '.' IDENTIFIER '(' (expression (',' expression)*)? ')'
           | INTEGER_LITERAL
           | 'true'
           | 'false'
           | IDENTIFIER
           | STRING_LITERAL
           | 'this'
           | 'new' 'int' '[' expression ']'
           | 'new' IDENTIFIER '(' ')'
           | '!' expression
           | '(' expression ')'
           | THIS
           | 'new' type '[' expression ']'
           | expression '[' expression ']'
           | IDENTIFIER '.' IDENTIFIER '(' (expression (',' expression)*)? ')'
           ;

// Lexer Rules
IF : 'if' ;
ELSE : 'else';
WHILE : 'while';
THIS : 'this' ;
INTEGER_LITERAL : [0-9]+ ;
STRING_LITERAL : '"' (~["\r\n])* '"' ;
IDENTIFIER : [a-zA-Z][a-zA-Z0-9]* ;
WS : [ \t\r\n]+ -> skip ;

