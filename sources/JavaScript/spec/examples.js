
var assertTrue = function (expr) {
  if (!expr) {
    throw new Error("Expression is false!");
  }
}

describe("Functions are first-order citizens", function () {
  it("Functions can be assigned to variables", function (){
    function times(x, y) { return x * y; }
    var timesVar = times;

    assertTrue(timesVar(3, 5) === 15);
  });

  it("Functions can be passed as function parameters", function (){
    function apply(func, arg) { return func(arg); }

    function times3(y) { return 3 * y; };

    assertTrue(apply(times3, 5) === 15);
  });

  it("Functions can be returned by functions", function (){
    function times(x) { return function (y) { return x * y; }; }

    assertTrue(times(3)(5) === 15);
  });
});

describe("Library functions", function () {
  it("filter selects elements from a collection", function () {
    var result = _.filter( [1, 2, 3, 4], function (x) { return x % 2 === 0; } );

    expect(result).toEqual([2, 4]);
  });

  it("map applies a function to each element of a collection", function () {
    var result = _.map( [1, 2, 3, 4], function (x) { return x + 5; } );

    expect(result).toEqual([6, 7, 8, 9]);
  });

  it("fold folds a collection, using a function", function () {
    var result = _.foldl( [2, 3, 4, 5], function (x, y) { return x * y; }, 1 );

    assertTrue(result === 120);
  });
});

describe("Square sum", function () {

  var square = function (i) { return i * i; };
  var add = function (s1, s2) { return s1 + s2; };

  it("range(1, 11) creates the sequence of numbers from 1 to 10", function () {
    var result = _.range(1, 11);

    expect(result).toEqual([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
  });

  it("map(..., square) squares a sequence of numbers", function () {
    var result = _.map([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], square);

    expect(result).toEqual([1, 4, 9, 16, 25, 36, 49, 64, 81, 100]);
  });

  it("reduce(..., add) sums a sequence of numbers", function () {
    var result = _.reduce([1, 4, 9, 16, 25, 36, 49, 64, 81, 100], add);

    assertTrue(result === 385);
  });

  it("Combining the individual steps", function () {
    var result = _.reduce(_.map(_.range(1, 11), square), add);

    assertTrue(result === 385);
  });

  it("Combining the individual steps from left to right", function () {
    var result = _(1).range(11).map(square).reduce(add);

    assertTrue(result === 385);
  });
});

