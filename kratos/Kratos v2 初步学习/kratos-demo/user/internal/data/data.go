package data

import (
	"github.com/go-kratos/kratos/v2/log"
	"github.com/google/wire"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	"time"
	"user/internal/biz"
	"user/internal/conf"
)

// ProviderSet is data providers.
var ProviderSet = wire.NewSet(NewData, NewDataBase, NewUseRepo)

// Data .
type Data struct {
	DataBase *gorm.DB // 数据库
}

// NewData .
func NewData(c *conf.Data, logger log.Logger, db *gorm.DB) (*Data, func(), error) {
	cleanup := func() {
		log.NewHelper(logger).Info("closing the data resources")
	}
	return &Data{
		DataBase: db,
	}, cleanup, nil
}

// NewDataBase 初始化数据库
func NewDataBase(c *conf.Data) (*gorm.DB, error) {
	// dsn 数据库链接
	// "用户名":"密码"@tcp("IP":"端口")/"数据库名称"?charset=utf8mb4&parseTime=True&loc=Local
	dsn := "test:test@tcp(localhost:3306)/kratos_demo?charset=utf8mb4&parseTime=True&loc=Local"
	db, err := gorm.Open(
		mysql.Open(dsn),
		&gorm.Config{})
	if err != nil {
		return nil, err
	}

	sqlDb, err := db.DB()
	if err != nil {
		return nil, err
	}
	// 设置连接池
	// 空闲
	sqlDb.SetMaxIdleConns(50)
	// 打开
	sqlDb.SetMaxOpenConns(100)
	// 超时
	sqlDb.SetConnMaxLifetime(time.Second * 30)

	err = DBAutoMigrate(db)
	if err != nil {
		return nil, err
	}

	return db, nil
}

// DBAutoMigrate 数据库模型自动迁移
// 在这里让GORM知道那些结构体是我们的数据模型，GORM将完成自动建表
func DBAutoMigrate(db *gorm.DB) error {
	err := db.AutoMigrate(
		&biz.User{}, // 加入用户数据模型
	)
	if err != nil {
		return err
	}

	return nil
}
