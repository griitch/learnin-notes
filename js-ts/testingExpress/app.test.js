const app = require("./app");
const request = require("supertest");

describe("Testing foo route", () => {
  // you can either use the jest api like this example
  it('returns { john : "doe"} on get request', async () => {
    const resp = await request(app).get("/foo");
    //console.log(resp);
    expect(resp.headers["content-type"]).toMatch(/json/);
    expect(resp.statusCode).toBe(200);
    expect(resp.headers["content-length"]).toBe("14");
  });

  // or you can daisy chain the supertest methods
  it("responds with json", (done) => {
    request(app)
      .post("/foo")
      .send({ name: "john" })
      .set("Accept", "application/json") // sets a header in the request
      .expect("Content-Type", /json/)
      .expect(200, done);
  });

  it("returns 400 on empty body", (done) => {
    request(app).post("/foo").send({}).expect(200, done);
  });
});
