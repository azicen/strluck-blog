// “service”目录下的文件是通过“/api”下的“*/proto”文件自动生成的
// 需要修改“UserService”的函数来完成消息的处理

package service

import (
	"context"
	"github.com/go-kratos/kratos/v2/log"
	pb "user/api/user/v1"
	"user/internal/biz"
)

// UserService 用户服务
type UserService struct {
	pb.UnimplementedUserServer

	// uc 用户操作的封装，在“/biz/user.go”中
	uc  *biz.UserUseCase
	log *log.Helper
}

func NewUserService(uc *biz.UserUseCase, logger log.Logger) *UserService {
	return &UserService{
		uc:  uc,
		log: log.NewHelper(logger),
	}
}

// CreateUser 创建用户服务接口
// 这里是一个给前端调用的接口
// 参数中的 req *pb.CreateUserRequest 为前端发送给后端的“创建用户请求参数”
// 如果成功返回一个 *pb.CreateUserReply 它是前端需要得到的回复“创建用户返回结果”
func (s *UserService) CreateUser(ctx context.Context, req *pb.CreateUserRequest) (*pb.CreateUserReply, error) {
	// 获取前端发送的Body数据
	body := req.GetCreatBody()
	// 创建用户
	id, err := s.uc.CreateUser(ctx, body.GetName(), body.GetPasswd())
	if err != nil {
		return nil, err
	}
	// 给前端返回数据
	return &pb.CreateUserReply{
		Id: uint64(id),
	}, nil
}

func (s *UserService) GetUser(ctx context.Context, req *pb.GetUserRequest) (*pb.GetUserReply, error) {
	return &pb.GetUserReply{}, nil
}
