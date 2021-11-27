package biz

import (
	"context"
	"github.com/go-kratos/kratos/v2/log"
	"gorm.io/gorm"
)

// User 用户数据模型
type User struct {
	// GORM的一个基础数据模型
	// 其中有ID、CreatedAt(创建时间)、UpdatedAt(更新时间)、DeletedAt(删除时间)
	gorm.Model
	// Name 用户的姓名
	// "not null" 定义该数据库字段不能为空
	// "type" 定义该数据库字段的类型，其类型为"varchar(32)"
	Name string `gorm:"not null; type:varchar(32)"`
	// Passwd 用户的密码
	Passwd string `gorm:"not null; type:char(16)"`
}

// UserRepo 将“用户数据模型”在数据库上的基础操作封装为接口
type UserRepo interface {
	// CreateUser 创建用户
	// 参数 model 为 User用户数据模型
	CreateUser(ctx context.Context, model *User) (uint, error)
	// GetUser 获取用户
	// 参数 id 为 User索引号
	GetUser(ctx context.Context, id uint) (*User, error)
}

// UserUseCase 将对用户的操作封装
type UserUseCase struct {
	repo UserRepo // 我们需要使用Repo来对数据库进行基础操作
	log  *log.Helper
}

func NewUserUseCase(repo UserRepo, logger log.Logger) *UserUseCase {
	return &UserUseCase{
		repo: repo,
		log:  log.NewHelper(logger),
	}
}

// 以下开始！
// 业务逻辑处理！

// CreateUser 创建用户
func (uc *UserUseCase) CreateUser(ctx context.Context, name, passwd string) (uint, error) {
	// 将“创建用户”函数的参数转换为“用户数据模型”
	userModel := &User{
		Name:   name,
		Passwd: passwd,
	}
	// 调用UserRepo接口实现中的CreateUser函数去创建用户
	return uc.repo.CreateUser(ctx, userModel)
}

// GetUser 获取用户
func (uc *UserUseCase) GetUser(ctx context.Context, id uint) (*User, error) {
	return uc.repo.GetUser(ctx, id)
}
