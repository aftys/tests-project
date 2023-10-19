const functions = require('./functions');
const reverseString = require('./reversestring');
const request = require('supertest');
const app =require('./app');

// CHECK FOR TRUTHY & FALSY VALUES
// toBeNull matches only null
// toBeUndefined matches only undefined
// toBeDefined is the opposite of toBeUndefined
// toBeTruthy matches anything that an if statement treats as true
// toBeFalsy matches anything that an if statement treats as false

// toBeNull
test('Should be null', () => {
  expect(functions.isNull()).toBeNull();
});

// toBeFalsy
test.skip('Should be falsy', () => {
  expect(functions.checkValue(undefined)).toBeFalsy();
});

// toEqual
test.skip('User should be Brad Traversy object', () => {
  expect(functions.createUser()).toEqual({
    firstName: 'Brad',
    lastName: 'Traversy'
  });
});

// Less than and greater than
test.skip('Should be under 1600', () => {
  const load1 = 800;
  const load2 = 800;
  expect(load1 + load2).toBeLessThanOrEqual(1600);
});

// Regex
test.skip('There is no I in team', () => {
  expect('team').not.toMatch(/I/i);
});

// Arrays
test.skip('Admin should be in usernames', () => {
  usernames = ['john', 'karen', 'admin'];
  expect(usernames).toContain('admin');
});

// Async Await
test.skip('User fetched name should be Leanne Graham', async () => {
  expect.assertions(1);
   const data = await functions.fetchUser();
  expect(data.name).toEqual('Leanne Graham');
});


////////////////////reversing test file

test.skip('reverseString function exists', () => {
  expect(reverseString).toBeDefined();
});

test.skip('String reverses', () => {
  expect(reverseString('hello')).toEqual('olleh');
});






////////////////////////////api test input 

describe.skip("when the username and password is missing", () => {
  test("should respond with a status code of 400", async () => {
    const bodyData = [
      {username: "username"},
      {password: "password"},
      {}
    ]
    for (const body of bodyData) {
      const response = await request(app).post("/users").send(body)
      expect(response.statusCode).toBe(400)
    }
  })
})
