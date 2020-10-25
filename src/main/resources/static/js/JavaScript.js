var personName;
var personAge;
if(confirm("Are you sure?")){
personName = prompt("Hi, what's your name?");
personAge = prompt("Hi, what's your age?");
alert("Привет, " + person + "!");}
else alert("Person not sure");
var sum;
function print (text){
document.write(text);
}
function count (x,y) {
sum = x+y;
}
print("Hello");
count(4, 5);
print("<br/>");
print(sum);