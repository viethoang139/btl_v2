Cách chạy chương trình:
  - Đưa code về IDE thông qua url trên github hoặc tải code rồi đưa vào IDE
  - Vào Mysql tạo ra 1 database có tên btl_v2
  - Vào file application.properties cài đặt password giống với password trong mysql của người dùng
  - Vào class BtlApplication rồi bấm nút mũi tên màu xanh để chạy chương trình
  * Note: khi đưa code về máy chương trình sẽ có thông báo yêu cầu bạn tải các file maven về hãy bấm nút "Load maven"
  * Note: khi chạy chương trình tất cả dữ liệu sẽ bị mất bao gồm cả user và role. Các bạn làm từng bước như sau
    + Khi chương trình khởi động thành công. Vào Mysql tìm bảng user và nhập chay thông tin của user bình thường trừ cột password do phải được mã hóa nên
      các bạn quay lại IDE tìm package utils ở đó có class tên là GeneratePassword các bạn nhập password dạng chuỗi (ví dụ "123") nó sẽ tạo ra một chuỗi mã hóa


         ![image](https://github.com/viethoang139/btl_v2/assets/93482932/8b5bbfd2-8b38-4a75-9f5d-cdb19578f9c0)





      ![image](https://github.com/viethoang139/btl_v2/assets/93482932/1d283cb0-487b-476c-ae64-355b77447ced)




     các bạn copy chuỗi đấy rồi paste vào trường dữ liệu ở cột password





    ![image](https://github.com/viethoang139/btl_v2/assets/93482932/3fe5866f-d0d5-481b-9fd8-a5229cc5ca07)






    * Note: Khi bạn bấm chạy class GeneratePassword sẽ tạo ra những chuỗi mã hóa khác nhau. Hãy copy chuỗi mà bạn vừa chạy xong
    + Đối với bảng roles: các bạn nhập chay cột id và ở cột name các bạn bắt buộc phải ghi là ROLE_ADMIN và ROLE_USER (do đây là mặc định của spring)
      
      Ví dụ:

      ![image](https://github.com/viethoang139/btl_v2/assets/93482932/5af96a43-7ab1-49d7-a34e-86ef4f24ef38)

      
    + Đối với bảng user_role: Đây là bảng thể hiện mối quan hệ giữa user và role. Các bạn hãy nhập chay user_id và role_id
   
      Ví dụ:


      ![image](https://github.com/viethoang139/btl_v2/assets/93482932/2e201f23-cafe-4cae-959b-6929e4688d64)


      Ảnh trên có nghĩa là người dùng có id là 1 và có quyền là admin, người dùng có id là 2 và quyền là user...

      Các bạn chỉ cần nhập chay như những gì mình đã hướng dẫn. Khi các bạn sử dụng tính năng đăng kí sẽ tự đông đăng kí người dùng với vai trò là user

      

Các bảng xử lý:
  - products: thông tin về sản phẩm
  - carts: chứa id của cart và id của người dùng
  - product_cart: bảng thể hiện mối quan hệ 1 nhiều và nhiều 1 (1 cart sẽ có nhiều product và nhiều product thuộc cùng 1 bảng), ngoài ra còn có cột quantity
    thể hiện số lượng của từng sản phẩm
  - users: chứa thông tin ngoài dùng
  - roles: chứa các quyền (ADMIN VÀ USER)
  - user_role: bảng thế hiện mối quan hệ nhiều nhiều giữa user và role

Tính năng:
  - Xử lí ngoại lệ
  - Đăng nhập: http://localhost:8080/api/auth/login (POST)
    + Tài khoản admin: (admin/123)
    + Tài khoản user: (duc/123), (dong/123)
  - Đăng kí: http://localhost:8080/api/auth/register (POST)
  - Product:
    + Tạo sản phẩm: http://localhost:8080/api/products (POST)
    + Truy xuất sản phẩm bởi id: http://localhost:8080/api/products/id (id là mã id mà người dùng cần nhập) (GET)
    + Truy xuất tất cả sản phẩm: http://localhost:8080/api/products (GET)
    + Cập nhật sản phẩm bởi id: http://localhost:8080/api/products/id (id là mã id mà người dùng cần nhập) (PUT)
    + Xóa sản phẩm theo id: http://localhost:8080/api/products/id (id là mã id mà người dùng cần nhập) (DELETE)
  - Cart:
    + Tạo ra giỏ hàng: http://localhost:8080/api/users/userId/carts (userId mà mã id của user mà người dùng cần nhập) (POST)
    + Thêm sản phẩm vào giỏ hàng: http://localhost:8080/api/carts/cartId/products/productId (cartId mà mã id của cart mà người dùng cần nhập, productId mà mã
      id của product mà người dùng cần nhập) (POST)
    + Xóa giỏ hàng bởi id: http://localhost:8080/api/cart/cartId (cartId mà mã id của cart mà người dùng cần nhập) (DELETE)
    + Truy xuất thông tin giỏ hàng (sẽ truy xuất tất cả sản phẩm trong 1 giỏ hàng): http://localhost:8080/api/carts/cartId (cartId mà mã id của cart mà người dùng cần nhập) (GET)
  - User
    + Truy xuất thông tin người dùng theo id (sẽ truy xuất thông tin người dùng kèm theo giỏ hàng và những sản phẩm có trong giỏ hàng đó):
      http://localhost:8080/api/users/userId (userId là mã id của user mà người dùng cần nhập) (GET)
    + Cập nhật thông tin của user bởi id: http://localhost:8080/api/users/userId (userId là mã id của user mà người dùng cần nhập) (PUT)
    + Xóa thông tin của user (sẽ xóa cả giỏ hàng và các sản phẩm trong giỏ hàng đó):  http://localhost:8080/api/users/userId (userId là mã id của user mà người dùng cần nhập) (DELETE)

Phân quyền:
  - Tài khoản admin có quyền: 
    + CRUD products
    + Xóa user
    + Cập nhật user
    + Truy xuất thông tin user
    + Xóa cart 
    + Truy vấn cart
  - Tài khoản user có quyền: 
    + Xóa user
    + Cập nhật user
    + Truy xuất thông tin user
    + Truy xuất thông tin products
  


