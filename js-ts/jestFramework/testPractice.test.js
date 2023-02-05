const practice = require("./testPractice");

describe("capitalize", () => {
  it("Capitalizes first letter of a word", () => {
    expect(practice.capitalize("omar")).toBe("Omar");
  });

  it("Leaves capitalized strings as they are", () => {
    expect(practice.capitalize("Omar")).toBe("Omar");
  });

  it("Returns an empty string for empty strings", () => {
    expect(practice.capitalize("")).toBe("");
  });
});

describe("reverseString", () => {
  it("reverses a string", () => {
    expect(practice.reverseString("omar")).toBe("ramo");
  });

  it("Returns an empty string for empty strings", () => {
    expect(practice.reverseString("")).toBe("");
  });
});

describe("ceasar cypher", () => {
  it("passes a basic test", () => {
    expect(practice.caesarCipher("abcd", 1)).toBe("bcde");
  });

  it("wraps around z", () => {
    expect(practice.caesarCipher("xyz", 1)).toBe("yza");
  });

  it("ignores punctuation", () => {
    expect(practice.caesarCipher(",;:!?./", 1)).toBe(",;:!?./");
  });

  it("does nothing on zero shifts", () => {
    expect(practice.caesarCipher("abcd", 0)).toBe("abcd");
  });
});
