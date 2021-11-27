package data

import (
	"context"
	"github.com/go-kratos/kratos/v2/log"
	"user/internal/biz"
)

// userRepo 实现 biz.UserRepo 接口
// 私有的结构体
// 对“/biz/user.go”中的UserRepo进行实现，
// 完成对“用户数据模型”的数据库基础操作。
type userRepo struct {
	data *Data // 数据库客户端的集合
	log  *log.Helper
}

// NewUseRepo .
// 注意这里返回的是 biz.UserRepo
// 在golang中接口的继承是不需要明确声明的
// 只要我们的私有结构体userRepo实现了“/biz/user.go”内 biz.UserRepo 接口内的方法
// 那么 userRepo 就是“继承”了 biz.UserRepo
func NewUseRepo(data *Data, logger log.Logger) biz.UserRepo {
	return &userRepo{
		data: data,
		log:  log.NewHelper(logger),
	}
}

// CreateUser 创建用户的数据库操作
// model 为用户数据模型
func (u userRepo) CreateUser(ctx context.Context, model *biz.User) (uint, error) {
	// 使用 “/data/date.go”中的 “Data”结构体内的 “DataBase *gorm.DB”数据库客户端在数据库中创建一个“用户数据”
	err := u.data.DataBase.Create(model).Error
	// 如果存在错误，则将错误返回
	if err != nil {
		return 0, err
	}
	// 如果没有发生错误，则返回创建成功后的用户id
	return model.ID, nil
}

func (u userRepo) GetUser(ctx context.Context, id uint) (*biz.User, error) {
	panic("implement me")
}
