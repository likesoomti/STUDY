require 'test_helper'

class CtrlControllerTest < ActionDispatch::IntegrationTest
  test "should get c_params" do
    get ctrl_c_params_url
    assert_response :success
  end

  test "should get c_request" do
    get ctrl_c_request_url
    assert_response :success
  end

  test "should get c_response" do
    get ctrl_c_response_url
    assert_response :success
  end

  test "should get c_headers" do
    get ctrl_c_headers_url
    assert_response :success
  end

end
