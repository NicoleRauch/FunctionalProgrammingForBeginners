
var assertTrue = function (expr) {
  if (!expr) {
    throw new Error("Expression is false!");
  }
}

describe("Funktionen sind first-order citizens", function () {
  it("Funktionen können Variablen zugewiesen werden", function (){
    function times(x, y) { return x * y; }
    var timesVar = times;

    assertTrue(timesVar(3, 5) === 15);
  });

  it("Funktionen können als Funktionsparameter übergeben werden", function (){
    function wendeAn(func, arg) { return func(arg); }

    function times3(y) { return 3 * y; };

    assertTrue(wendeAn(times3, 5) === 15);
  });

  it("Funktionen können von Funktionen zurückgegeben werden", function (){
    function times(x) { return function (y) { return x * y; }; }

    assertTrue(times(3)(5) === 15);
  });
});

describe("Closures", function (){
  it("Wirkungsweise", function () {
    function neueClosure(zahl) {
      var zaehler = zahl;
      return function (inkrement) {
        zaehler += inkrement;
        return zaehler;
      }
    }

    var closure1 = neueClosure(10);
    var closure2 = neueClosure(20);
    assertTrue(closure1(5) === 15);
    assertTrue(closure2(5) === 25);
    assertTrue(closure1(5) === 20);
    assertTrue(closure2(5) === 30);
  });
});

describe("Bibliotheksfunktionen", function () {
  it("filter selektiert Elemente aus einer Collection", function () {
    var result = _.filter( [1, 2, 3, 4], function (x) { return x % 2 === 0; } );

    expect(result).toEqual([2, 4]);
  });

  it("map wendet eine Funktion auf jedes Element einer Collection an", function () {
    var result = _.map( [1, 2, 3, 4], function (x) { return x + 5; } );

    expect(result).toEqual([6, 7, 8, 9]);
  });

  it("fold (simpler Aufruf) faltet eine Collection mit Hilfe einer Funktion zusammen", function () {
    var result = _.foldl( [2, 3, 4, 5], function (x, y) { return x * y; } );

    assertTrue(result === 120);
  });

  it("fold (mit Initialwert) faltet eine Collection mit Hilfe einer Funktion zusammen", function () {
    var result = _.foldl( [2, 3, 4, 5], function (x, y) { return x * y; }, 1 );

    assertTrue(result === 120);
  });
});

describe("Quadratsumme", function () {

  var quadriere = function (i) { return i * i; };
  var addiere = function (summe, summand) { return summe + summand; };

  it("range(1, 11) erzeugt die Folge der Zahlen von 1 bis 10", function () {
    var result = _.range(1, 11);

    expect(result).toEqual([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
  });

  it("map(..., quadriere) quadriert eine Zahlenfolge", function () {
    var result = _.map([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], quadriere);

    expect(result).toEqual([1, 4, 9, 16, 25, 36, 49, 64, 81, 100]);
  });

  it("reduce(..., addiere) summiert eine Zahlenfolge", function () {
    var result = _.reduce([1, 4, 9, 16, 25, 36, 49, 64, 81, 100], addiere);

    assertTrue(result === 385);
  });

  it("Verbindung der Einzelschritte", function () {
    var result = _.reduce(_.map(_.range(1, 11), quadriere), addiere);

    assertTrue(result === 385);
  });

  it("Verbindung der Einzelschritte von links nach rechts", function () {
    var result = _(1).range(11).map(quadriere).reduce(addiere);

    assertTrue(result === 385);
  });
});

