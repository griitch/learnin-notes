/**
 *  an important concept of tdd is isolation : we only test one func at a time, and those tests
 *  should not depend on any external function behaving correctly, (esp if that func was being
 *  tested somewhere else )
 *
 *  - this will make it easier to narrow down the cause of the failure of a test
 *
 *  - to help us with this, we can either try to use pure functions, or mock external funcs and
 *  have them return some dummy result
 *
 *
 *  mocks in jest : erasing implementations of a func, capturin calls to the func, capturing calls to
 *  a constructoh with new, and mock return values
 */

// using a mock function
test("mock func test ", () => {
  const mockFunc = jest.fn((x) => x * 2);
  mockFunc(7);
  expect(mockFunc.mock.calls.length).toBeGreaterThan(0);
  expect(mockFunc.mock.calls[0][0]).toBe(7); // calls is a 2d array ,[call number][call argument]
  expect(mockFunc.mock.results[0].value).toBe(14);
  // other xampls on the docs
});

// making a mock func return a value we want
test("mock function return vales", () => {
  const mockFunc = jest.fn();
  // mockFunc() at this stage returns undefined

  mockFunc
    .mockReturnValueOnce("first")
    .mockReturnValueOnce("second")
    .mockReturnValue("rest of calls");

  mockFunc();
  mockFunc();
  mockFunc();
  mockFunc();
  expect(mockFunc.mock.results[0].value).toBe("first");
  expect(mockFunc.mock.results[1].value).toBe("second");
  expect(mockFunc.mock.results[2].value).toBe("rest of calls");
  expect(mockFunc.mock.results[3].value).toBe("rest of calls");
});

// mocking a function from a module (xmpl mock a network request)
// we can for example do jest.mock(axios)

const sum = require("./sum");

jest.mock("./sum", () => {
  return jest.fn(() => console.log("i am a mock of sum"));
});

test.only("testing mocking the sum func", () => {
  sum();
  console.log(sum(5, 3));
  expect(1).toBe(1);
});
