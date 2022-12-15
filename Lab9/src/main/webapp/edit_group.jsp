<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="resources"/>

<jsp:include page="layout.jsp"></jsp:include>


<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

<style>
  button,
  input {
    font-family: "Montserrat", "Helvetica Neue", Arial, sans-serif;
  }

  body {
    color: #2c2c2c;
    font-size: 14px;
    font-family: Montserrat, Helvetica Neue, Arial, sans-serif;
    overflow-x: hidden;
    -moz-osx-font-smoothing: grayscale;
    -webkit-font-smoothing: antialiased;
  }

  @font-face {
    font-family: 'Nucleo Outline';
    src: url("https://github.com/creativetimofficial/now-ui-kit/blob/master/assets/fonts/nucleo-outline.eot");
    src: url("https://github.com/creativetimofficial/now-ui-kit/blob/master/assets/fonts/nucleo-outline.eot") format("embedded-opentype");
    src: url("https://raw.githack.com/creativetimofficial/now-ui-kit/master/assets/fonts/nucleo-outline.woff2");
    font-weight: normal;
    font-style: normal;
  }

  .btn,

  .btn:hover,
  .btn:focus,
  .btn:not(:disabled):not(.disabled):active,
  .btn:not(:disabled):not(.disabled):active:focus,
  .btn:active:hover,


  .btn:hover,


  .btn:disabled,
  .btn:disabled:hover,
  .btn:disabled:focus,
  .btn:disabled:active,
  .btn[disabled],
  .btn[disabled]:hover,
  .btn[disabled]:focus,
  .btn[disabled]:active,
  .navbar .navbar-nav>a.btn:disabled,
  .navbar .navbar-nav>a.btn:disabled:hover,
  .navbar .navbar-nav>a.btn:disabled:focus,
  .navbar .navbar-nav>a.btn:disabled:active,
  .navbar .navbar-nav>a.btn[disabled],
  .navbar .navbar-nav>a.btn[disabled]:hover,
  .navbar .navbar-nav>a.btn[disabled]:focus,
  .navbar .navbar-nav>a.btn[disabled]:active {
    background-color: #888888;
    border-color: #888888;
  }

  .btn:hover,
  .btn:focus,
  .navbar .navbar-nav>a.btn:hover,
  .navbar .navbar-nav>a.btn:focus {
    opacity: 1;
    filter: alpha(opacity=100);
    outline: 0 !important;
  }

  .btn:active,
  .navbar .navbar-nav>a.btn:active {
    -webkit-box-shadow: none;
    box-shadow: none;
    outline: 0 !important;
  }

  .btn.btn-icon,
  .navbar .navbar-nav>a.btn.btn-icon {
    height: 2.375rem;
    min-width: 2.375rem;
    width: 2.375rem;
    padding: 0;
    font-size: 0.9375rem;
    overflow: hidden;
    position: relative;
    line-height: normal;
  }

  .btn.btn-icon.btn-lg,
  .navbar .navbar-nav>a.btn.btn-icon.btn-lg {
    height: 3.6rem;
    min-width: 3.6rem;
    width: 3.6rem;
  }

  .btn.btn-icon.btn-lg i.now-ui-icons,
  .btn.btn-icon.btn-lg i.fab,
  .navbar .navbar-nav>a.btn.btn-icon.btn-lg i.now-ui-icons,
  .navbar .navbar-nav>a.btn.btn-icon.btn-lg i.fab {
    font-size: 1.325rem;
  }

  .btn.btn-icon:not(.btn-footer) i.now-ui-icons,
  .btn.btn-icon:not(.btn-footer) i.fab,
  .navbar .navbar-nav>a.btn.btn-icon:not(.btn-footer) i.now-ui-icons,
  .navbar .navbar-nav>a.btn.btn-icon:not(.btn-footer) i.fab {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-12px, -12px);
    line-height: 1.5626rem;
    width: 23px;
  }

  .btn:not(.btn-icon) .now-ui-icons,
  .navbar .navbar-nav>a.btn:not(.btn-icon) .now-ui-icons {
    position: relative;
    top: 1px;
  }

  .btn-neutral {
    background-color: #FFFFFF;
    color: #f96332;
  }

  .btn-neutral:hover,
  .btn-neutral:focus,
  .btn-neutral:not(:disabled):not(.disabled):active,
  .btn-neutral:not(:disabled):not(.disabled):active:focus,
  .btn-neutral:active:hover {
    background-color: #FFFFFF;
    color: #FFFFFF;
    box-shadow: none;
  }

  .btn-neutral:hover {
    box-shadow: 0 3px 8px 0 rgba(0, 0, 0, 0.17);
  }

  .btn-neutral:disabled,
  .btn-neutral:disabled:hover,
  .btn-neutral:disabled:focus,
  .btn-neutral:disabled:active,
  .btn-neutral[disabled],
  .btn-neutral[disabled]:hover,
  .btn-neutral[disabled]:focus,
  .btn-neutral[disabled]:active {
    background-color: #FFFFFF;
    border-color: #FFFFFF;
  }

  .btn-neutral:hover,
  .btn-neutral:focus,
  .btn-neutral:not(:disabled):not(.disabled):active,
  .btn-neutral:not(:disabled):not(.disabled):active:focus,
  .btn-neutral:active:hover {
    background-color: #FFFFFF;
    color: #fa7a50;
    box-shadow: none;
  }

  .btn-neutral:hover,
  .btn-neutral:focus {
    color: #fa7a50;
  }

  .btn-neutral:hover:not(.nav-link),
  .btn-neutral:focus:not(.nav-link) {
    box-shadow: none;
  }

  .btn-round {
    border-width: 1px;
    border-radius: 30px !important;
    padding: 11px 23px;
  }

  .btn-lg {
    font-size: 1em;
    border-radius: 0.25rem;
    padding: 15px 48px;
  }

  .btn-facebook,
  .btn-facebook:hover,
  .btn-facebook:active,
  .btn-facebook:active:focus {
    color: #3b5998 !important;
  }

  .btn-twitter,
  .btn-twitter:hover,
  .btn-twitter:active,
  .btn-twitter:active:focus {
    color: #55acee !important;
  }

  .btn-google,
  .btn-google:hover,
  .btn-google:active,
  .btn-google:active:focus {
    color: #dd4b39 !important;
  }

  .form-control::-moz-placeholder {
    color: #888888;
    opacity: 0.8;
    filter: alpha(opacity=80);
  }

  .form-control:-moz-placeholder {
    color: #888888;
    opacity: 0.8;
    filter: alpha(opacity=80);
  }

  .form-control::-webkit-input-placeholder {
    color: #888888;
    opacity: 0.8;
    filter: alpha(opacity=80);
  }

  .form-control:-ms-input-placeholder {
    color: #888888;
    opacity: 0.8;
    filter: alpha(opacity=80);
  }

  .form-control {
    background-color: transparent;
    border: 1px solid #E3E3E3;
    border-radius: 30px;
    color: #2c2c2c;
    line-height: normal;
    font-size: 0.8571em;
    -webkit-transition: color 0.3s ease-in-out, border-color 0.3s ease-in-out, background-color 0.3s ease-in-out;
    -moz-transition: color 0.3s ease-in-out, border-color 0.3s ease-in-out, background-color 0.3s ease-in-out;
    -o-transition: color 0.3s ease-in-out, border-color 0.3s ease-in-out, background-color 0.3s ease-in-out;
    -ms-transition: color 0.3s ease-in-out, border-color 0.3s ease-in-out, background-color 0.3s ease-in-out;
    transition: color 0.3s ease-in-out, border-color 0.3s ease-in-out, background-color 0.3s ease-in-out;
    -webkit-box-shadow: none;
    box-shadow: none;
  }

  .form-control:focus {
    border: 1px solid #f96332;
    -webkit-box-shadow: none;
    box-shadow: none;
    outline: 0 !important;
    color: #2c2c2c;
  }

  .form-control:focus+.input-group-text,
  .form-control:focus~.input-group-text {
    border: 1px solid #f96332;
    border-left: none;
    background-color: transparent;
  }

  .input-group.no-border .form-control {
    padding: 11px 19px;
  }

  .input-group.no-border .form-control+.input-group-text {
    padding: 11px 19px 11px 0;
  }

  .input-group.no-border .input-group-text {
    padding: 11px 0 11px 19px;
  }

  .input-group .form-control {
    padding: 10px 18px 10px 18px;
  }

  .input-group .form-control+.input-group-text {
    padding: 10px 18px 10px 0;
  }

  .input-group .input-group-text {
    padding: 10px 0 10px 18px;
  }

  .input-group .input-group-text+.form-control,
  .input-group .input-group-text~.form-control {
    padding: 10px 19px 11px 16px;
  }

  .input-group.no-border .form-control,
  .input-group.no-border .form-control+.input-group-text {
    background-color: rgba(222, 222, 222, 0.3);
    border: medium none;
  }

  .input-group.no-border .form-control:focus,
  .input-group.no-border .form-control:active,
  .input-group.no-border .form-control:active,
  .input-group.no-border .form-control+.input-group-text:focus,
  .input-group.no-border .form-control+.input-group-text:active,
  .input-group.no-border .form-control+.input-group-text:active {
    border: medium none;
    background-color: rgba(222, 222, 222, 0.5);
  }

  .input-group.no-border .form-control:focus+.input-group-text {
    background-color: rgba(222, 222, 222, 0.5);
  }

  .input-group.no-border .input-group-prepend .input-group-text {
    background-color: rgba(222, 222, 222, 0.3);
    border: none;
    border-left: transparent !important;
  }

  .input-group-text {
    background-color: #FFFFFF;
    border: 1px solid #E3E3E3;
    border-radius: 30px;
    color: #555555;
    -webkit-transition: color 0.3s ease-in-out, border-color 0.3s ease-in-out, background-color 0.3s ease-in-out;
    -moz-transition: color 0.3s ease-in-out, border-color 0.3s ease-in-out, background-color 0.3s ease-in-out;
    -o-transition: color 0.3s ease-in-out, border-color 0.3s ease-in-out, background-color 0.3s ease-in-out;
    -ms-transition: color 0.3s ease-in-out, border-color 0.3s ease-in-out, background-color 0.3s ease-in-out;
    transition: color 0.3s ease-in-out, border-color 0.3s ease-in-out, background-color 0.3s ease-in-out;
  }

  .input-group-text+.form-control,
  .input-group-text~.form-control {
    padding: -0.5rem 0.7rem;
    padding-left: 18px;
  }

  .input-group-text i {
    width: 17px;
  }

  .input-group {
    margin-bottom: 10px;
    position: relative;
  }

  .input-group[disabled] .input-group-text {
    background-color: #E3E3E3;
  }

  .input-group .input-group-prepend {
    margin-right: 0;
  }

  .input-group .input-group-prepend .input-group-text {
    border-left: 1px solid #E3E3E3;
  }

  .input-group .form-control:first-child,
  .input-group-text:first-child {
    border-right: 0 none;
  }

  .input-group .form-control:last-child,
  .input-group-text:last-child {
    border-left: 0 none;
  }

  .form-control[disabled],
  .form-control[readonly] {
    background-color: #E3E3E3;
    color: #888888;
    cursor: not-allowed;
  }

  button,
  input {
    font-family: "Montserrat", "Helvetica Neue", Arial, sans-serif;
  }

  h3 {
    font-weight: 400;
  }

  a {
    color: #f96332;
  }

  a:hover,
  a:focus {
    color: #f96332;
  }

  h3,
  .h3 {
    font-size: 1.825em;
    margin-bottom: 30px;
    line-height: 1.4em;
  }

  p {
    line-height: 1.61em;
    font-weight: 300;
    font-size: 1.2em;
  }

  .title {
    font-weight: 700;
    padding-top: 30px;
  }

  .title.title-up {
    text-transform: uppercase;
  }

  .title.title-up a {
    color: #2c2c2c;
    text-decoration: none;
  }

  .card a {
    -webkit-transition: all 150ms ease 0s;
    -moz-transition: all 150ms ease 0s;
    -o-transition: all 150ms ease 0s;
    -ms-transition: all 150ms ease 0s;
    transition: all 150ms ease 0s;
  }


  .button-bar {
    display: block;
    position: relative;
    width: 22px;
    height: 1px;
    border-radius: 1px;
    background: #FFFFFF;
  }

  .button-bar+.button-bar {
    margin-top: 7px;
  }

  .button-bar:nth-child(2) {
    width: 17px;
  }

  .title-up {
    text-transform: uppercase;
  }

  @font-face {
    font-family: 'Nucleo Outline';
    src: url("../fonts/nucleo-outline.eot");
    src: url("../fonts/nucleo-outline.eot") format("embedded-opentype"), url("../fonts/nucleo-outline.woff2") format("woff2"), url("../fonts/nucleo-outline.woff") format("woff"), url("../fonts/nucleo-outline.ttf") format("truetype");
    font-weight: normal;
    font-style: normal;
  }

  .now-ui-icons {
    display: inline-block;
    font: normal normal normal 14px/1 'Nucleo Outline';
    font-size: inherit;
    speak: none;
    text-transform: none;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  @-webkit-keyframes nc-icon-spin {
    0% {
      -webkit-transform: rotate(0deg);
    }

    100% {
      -webkit-transform: rotate(360deg);
    }
  }

  @-moz-keyframes nc-icon-spin {
    0% {
      -moz-transform: rotate(0deg);
    }

    100% {
      -moz-transform: rotate(360deg);
    }
  }

  @keyframes nc-icon-spin {
    0% {
      -webkit-transform: rotate(0deg);
      -moz-transform: rotate(0deg);
      -ms-transform: rotate(0deg);
      -o-transform: rotate(0deg);
      transform: rotate(0deg);
    }

    100% {
      -webkit-transform: rotate(360deg);
      -moz-transform: rotate(360deg);
      -ms-transform: rotate(360deg);
      -o-transform: rotate(360deg);
      transform: rotate(360deg);
    }
  }

  .now-ui-icons.ui-1_email-85:before {
    content: "\ea2a";
  }

  .now-ui-icons.text_caps-small:before {
    content: "\ea1b";
  }

  .now-ui-icons.users_circle-08:before {
    content: "\ea23";
  }

  .bg-primary {
    background-color: #f96332 !important;
  }

  img {
    max-width: 100%;
    border-radius: 1px;
  }


  .card {
    border: 0;
    border-radius: 0.1875rem;
    display: inline-block;
    position: relative;
    width: 100%;
    margin-bottom: 30px;
    box-shadow: 0px 5px 25px 0px rgba(0, 0, 0, 0.2);
  }

  .card .card-header {
    background-color: transparent;
    border-bottom: 0;
    background-color: transparent;
    border-radius: 0;
    padding: 0;
  }

  .card .card-footer {
    padding: 0;
    margin-top: 15px;
    background-color: transparent;
    border: 0;
  }

  .card[data-background-color="orange"] {
    background-color: #f96332;
  }

  .card[data-background-color="red"] {
    background-color: #FF3636;
  }

  .card[data-background-color="yellow"] {
    background-color: #FFB236;
  }

  .card[data-background-color="blue"] {
    background-color: #2CA8FF;
  }

  .card[data-background-color="green"] {
    background-color: #15b60d;
  }

  .card-signup {
    max-width: 350px;
    margin: 0 auto;
  }

  .card-signup .card-header {
    margin: 0 20px;
    padding: 30px 0;
  }

  .card-signup .card-title {
    margin-top: 15px;
    margin-bottom: 15px;
  }

  .card-signup .card-footer {
    margin-bottom: 10px;
    margin-top: 24px;
    padding: 24px 0;
  }

  .card-signup .card-body {
    padding-top: 0px;
    padding-bottom: 0px;
    min-height: auto;
  }

  .card-signup .social-line {
    margin-top: 20px;
    text-align: center;
  }

  .card-signup .social-line .btn.btn-icon,
  .card-signup .social-line .btn.btn-icon .btn-icon {
    margin-left: 5px;
    margin-right: 5px;
    margin-top: 0;
    margin-bottom: 0;
    box-shadow: 0px 5px 50px 0px rgba(0, 0, 0, 0.2);
  }

  .section {
    padding: 70px 0;
    position: relative;
    background: #FFFFFF;
  }

  .section-signup {
    padding-top: 20vh;
  }

  [data-background-color="orange"] {
    background-color: #e95e38;
  }

  [data-background-color="black"] {
    background-color: #2c2c2c;
  }

  [data-background-color]:not([data-background-color="gray"]) {
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) .title,
  [data-background-color]:not([data-background-color="gray"]) p {
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) h3,
  [data-background-color]:not([data-background-color="gray"]) a:not(.btn):not(.dropdown-item) {
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) .input-group-text,
  [data-background-color]:not([data-background-color="gray"]) .input-group.no-border .input-group-text {
    color: rgba(255, 255, 255, 0.8);
  }

  [data-background-color]:not([data-background-color="gray"]) .checkbox label::before,
  [data-background-color]:not([data-background-color="gray"]) .checkbox label::after {
    border-color: rgba(255, 255, 255, 0.2);
  }

  [data-background-color]:not([data-background-color="gray"]) .checkbox label::after,
  [data-background-color]:not([data-background-color="gray"]) .checkbox label {
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) .checkbox input[type="checkbox"]:disabled+label {
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) .form-control::-moz-placeholder {
    color: #ebebeb;
    opacity: 1;
    filter: alpha(opacity=100);
  }

  [data-background-color]:not([data-background-color="gray"]) .form-control:-moz-placeholder {
    color: #ebebeb;
    opacity: 1;
    filter: alpha(opacity=100);
  }

  [data-background-color]:not([data-background-color="gray"]) .form-control::-webkit-input-placeholder {
    color: #ebebeb;
    opacity: 1;
    filter: alpha(opacity=100);
  }

  [data-background-color]:not([data-background-color="gray"]) .form-control:-ms-input-placeholder {
    color: #ebebeb;
    opacity: 1;
    filter: alpha(opacity=100);
  }

  [data-background-color]:not([data-background-color="gray"]) .form-control {
    border-color: rgba(255, 255, 255, 0.5);
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) .form-control:focus {
    border-color: #FFFFFF;
    background-color: transparent;
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) .input-group-text {
    background-color: transparent;
    border-color: rgba(255, 255, 255, 0.5);
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) .input-group.no-border .form-control {
    background-color: rgba(255, 255, 255, 0.1);
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) .input-group.no-border .form-control:focus,
  [data-background-color]:not([data-background-color="gray"]) .input-group.no-border .form-control:active,
  [data-background-color]:not([data-background-color="gray"]) .input-group.no-border .form-control:active {
    background-color: rgba(255, 255, 255, 0.2);
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) .input-group.no-border .form-control+.input-group-text {
    background-color: rgba(255, 255, 255, 0.1);
  }

  [data-background-color]:not([data-background-color="gray"]) .input-group.no-border .form-control+.input-group-text:focus,
  [data-background-color]:not([data-background-color="gray"]) .input-group.no-border .form-control+.input-group-text:active,
  [data-background-color]:not([data-background-color="gray"]) .input-group.no-border .form-control+.input-group-text:active {
    background-color: rgba(255, 255, 255, 0.2);
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) .input-group.no-border .form-control:focus+.input-group-text {
    background-color: rgba(255, 255, 255, 0.2);
    color: #FFFFFF;
  }

  [data-background-color]:not([data-background-color="gray"]) .input-group.no-border .input-group-text {
    background-color: rgba(255, 255, 255, 0.1);
    border: none;
    color: #FFFFFF;
  }


</style>

<div class="section section-signup">
  <div class="container">
    <div class="row">
      <div class="card card-signup" data-background-color="black">
        <form action="../home" method="post" enctype="multipart/form-data">
          <input name="command" type="hidden" value="getEditedGroup">
          <div class="card-header text-center">
            <h3 class="card-title title-up">Edit student</h3>
          </div>
          <div class="card-body">
            <div class="input-group no-border">
              <div class="input-group-prepend">
                              <span class="input-group-text">
                                <i class="now-ui-icons users_circle-08"></i>
                              </span>
              </div>
              <input type="text" name="name" value="${group.name}">
            </div>
            <div class="input-group no-border">
              <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="now-ui-icons users_circle-08"></i>
                  </span>
              </div>

              <input type="number" name="course" value="${group.course}">
            </div>

            <input name="id" type="hidden" value="${group.id}">

          </div>
          <div class="card-footer text-center">
            <button class="btn btn-danger btn-round btn-lg" type="submit">Edit group</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>