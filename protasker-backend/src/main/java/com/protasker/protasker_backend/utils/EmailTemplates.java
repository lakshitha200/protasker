package com.protasker.protasker_backend.utils;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplates {

    public String getVerificationEmailHtmlContext(String token){
        String verificationLink = "http://localhost:4200/verify-email?token=" + token;
        String htmlContext = """
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body {
                          background-color: #f3f4f6;
                          font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                          margin: 0;
                          padding: 0;
                        }
                        .email-container {
                              background: #ffffff;
                              max-width: 600px;
                              margin: 30px auto;
                              padding: 20px 30px;
                              border-radius: 10px;
                              box-shadow: 0 6px 18px rgba(0, 0, 0, 0.05);
                              text-align: center;
                        }
                        h2 {
                              color: #333;
                        }
                        p {
                          color: #555;
                          font-size: 16px;
                          line-height: 1.6;
                        }
                        .button {
                          display: inline-block;
                          margin-top: 25px;
                          padding: 12px 30px;
                          background-color: #7968ff;
                          color: #ffffff !important;
                          text-decoration: none;
                          border-radius: 6px;
                          font-weight: 600;
                        }
                        .footer {
                          margin-top: 40px;
                          font-size: 13px;
                          color: #999;
                        }
                    </style>
                </head>
                <body>
                    <div class="email-container">
                        <h2>Welcome to ProTasker!</h2>
                        <p>Thanks for signing up. To get started, please verify your email:</p>
                        <a href="%s" class="button">Verify My Email</a>
                        <p><small style="color: #7968ff;">Link expires in 24 hours. Can’t click? Paste this into your browser:<br>%s</small></p>
                        <p class="footer">
                          Need help? Visit our <a href="https://protasker.app/support" style="color: #7968ff;">Support Center</a>.
                          <br><br>
                          Cheers, <br>
                          The ProTasker Team
                        </p>
                    </div>
                </body>
                </html>
                
                """;
        return String.format(htmlContext, verificationLink, verificationLink);
    }

    public String getWelcomeEmailHtmlContext(){
        String htmlContext = """
                <!DOCTYPE html>
                <html>
                <head>
                  <meta charset="UTF-8">
                  <style>
                    body {
                      background-color: #f3f4f6;
                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                      margin: 0;
                      padding: 0;
                    }
                    .email-container {
                      background: #ffffff;
                      max-width: 600px;
                      margin: 30px auto;
                      padding: 20px 30px;
                      border-radius: 10px;
                      box-shadow: 0 6px 18px rgba(0, 0, 0, 0.05);
                      text-align: center;
                    }
                    h2 {
                      color: #333;
                    }
                    p {
                      color: #555;
                      font-size: 16px;
                      line-height: 1.6;
                    }
                    .button {
                      display: inline-block;
                      margin-top: 25px;
                      padding: 12px 30px;
                      background-color: #7968ff;
                      color: #ffffff !important;
                      text-decoration: none;
                      border-radius: 6px;
                      font-weight: 600;
                    }
                    .footer {
                      margin-top: 40px;
                      font-size: 13px;
                      color: #999;
                    }
                  </style>
                </head>
                <body>
                  <div class="email-container">
                    <h2>Welcome to ProTasker! 🎉</h2>
                    <p>Your email has been successfully verified.</p>
                    <p>We're thrilled to have you on board. Now you can start managing your projects, collaborating with your team, and staying productive like never before.</p>
                    <a href="http://localhost:4200/dashboard" class="button">Go to Dashboard</a>
                    <p class="footer">
                      Need help? Visit our <a href="https://protasker.app/support" style="color: #7968ff;">Support Center</a>.
                      <br><br>
                      Cheers, <br>
                      The ProTasker Team
                    </p>
                  </div>
                </body>
                </html>
                                
                
                """;
        return htmlContext;
    }
}
