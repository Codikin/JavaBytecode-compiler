grammar MiniJava;

// Parser Rules

program : mainClass (classDeclaration)* EOF ;

mainClass : 'class' IDENTIFIER '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' IDENTIFIER ')' '{' statement '}' '}' ;

classDeclaration : 'class' IDENTIFIER ('extends' IDENTIFIER)? '{' (varDeclaration)* (methodDeclaration)* '}' ;

varDeclaration : type statement  ;

methodDeclaration : 'public' type IDENTIFIER '(' (type IDENTIFIER (',' type IDENTIFIER)*)? ')' '{' (varDeclaration)* (statement)* 'return' expression ';' '}' ;

type : 'int' '[' ']' | 'boolean' | 'int' | IDENTIFIER ;

statement : '{' (statement)* '}'
          | 'if' '(' expression ')' statement 'else' statement
          | 'while' '(' expression ')' statement
          | 'System.out.println' '(' '"' expression '"' ')' ';'
          | 'System.out.println' '(' expression ')' ';'
          | IDENTIFIER '=' expression ';'
          | IDENTIFIER '[' expression ']' '=' expression ';'
          ;

expression : expression ('&&' | '<' | '+') expression
           | expression '[' expression ']'
           | expression '.' 'length'
           | expression '.' IDENTIFIER '(' (expression (',' expression)*)? ')'
           | INTEGER_LITERAL
           | 'true'
           | 'false'
           | IDENTIFIER
           | 'this'
           | 'new' 'int' '[' expression ']'
           | 'new' IDENTIFIER '(' ')'
           | '!' expression
           | '(' expression ')'
           ;

// Lexer Rules

INTEGER_LITERAL : [0-9]+ ;
IDENTIFIER : [a-zA-Z][a-zA-Z0-9]* ;
WS : [ \t\r\n]+ -> skip ;
