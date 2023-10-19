const express=require('express');


///////////////////////////////// test missing data


const app = express()

  app.use(express.json())
  app.post('/users', async (req, res) => {
    const { password, username } = req.body
    if (!password || !username) {
      res.sendStatus(400)
      return
    }


    res.send({ userId:0 })
  })
  app.listen(8081, ()=>{console.log("listening on port 8081")})


  module.exports = app;
  
