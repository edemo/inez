grammar Bridi;

fragment LETTER : [_a-zA-Z0-9$?: .,];

PRE : '{';
POST: '}';
LITERALPRE: '{[';
LITERALPOST: ']}';
REF: '@';
NEWLINE: '\n';

TEXT   : (LETTER)+ ;

literal:  (TEXT|PRE|POST)+ ;

bridi: (TEXT|LITERALPRE literal LITERALPOST|PRE bridi POST | PRE textReference POST)+;
textReference: REF TEXT;

paragraph: (bridi NEWLINE)* bridi;