grammar Bridi;

fragment LETTER : [a-zA-Z0-9$? ];

TEXT   : LETTER+ ;
PRE : '{';
POST: '}';
REF: '@';

bridi: (TEXT|PRE bridi POST | PRE textReference POST)+;
textReference: REF TEXT;
